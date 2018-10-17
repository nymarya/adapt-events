package br.com.adapt.service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.domain.Type;
import br.com.adapt.exception.InvalidTaskException;
import br.com.adapt.model.Scheduler;
import br.com.adapt.model.Tag;
import br.com.adapt.model.Task;
import br.com.adapt.model.User;
import br.com.adapt.repository.TaskRepository;


/**
 * @author Jaine
 *
 */
@Service
@Transactional(readOnly = true)
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Transactional(readOnly = false)
	public Task saveTask(Task entity) {
		Task t = taskRepository.save(entity);
		return t;
	}
	
	@Transactional(readOnly = false)
	public Task saveTask(Task entity, Scheduler scheduler) throws InvalidTaskException {
        entity.setScheduler(scheduler);
        //Titulo, descrição e dificuldade sempre são obrigatorios
        if( entity.getDificulty() == null || entity.getTitle().isEmpty() || entity.getDescription().isEmpty()) {
        	throw new InvalidTaskException("Tarefa inválida.");
        } 
        
        //Se for rotina, os campos dia, hora de inicio e fim são obrigatorios
        if( entity.getType() == Type.ROUTINE) {
        	Integer day = entity.getDay();
        	if(entity.getEndDate() == null || entity.getStartDate() == null || day == null) {
        		throw new InvalidTaskException("Tarefa inválida.");
        	}
        } else { //Se não for rotina, prioridade e tempo esperado são obrigatorios
        	Integer expectedTime = entity.getExpectedTime();
        	if(entity.getPriority() == null || expectedTime == 0) {
        		throw new InvalidTaskException("Tarefa inválida.");
        	}
        	entity.setStartDate(null);
        	entity.setEndDate(null);
        }
        
        // Se não 
		return taskRepository.save(entity);
	}
	
	@Transactional
	public Task findById(int id) {
		return taskRepository.findById(id);
	}
	
	@Transactional(readOnly=false)
	public void delete(Task entity) {
		taskRepository.delete(entity);
	}

	/**
	 * Recupera todas as tarefas do usuário logado
	 * @param name
	 * @return
	 */
	public List<Task> findByUserEmail(String name) {
		List<Task> tasks= taskRepository.findByUserEmail(name);
		return tasks;
	}
	
	/**
	 * recupera todas as tarefas temporarias do usuario logado
	 * @return
	 */
	public List<Task> findTemporaryByUserAuthenticated(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Task> tasks= taskRepository.findTemporaryByUserEmail(auth.getName());
		return tasks;
	}

	/**
	 * Atualiza os status das tarefas após o check-in
	 * @param checkins
	 * @return
	 * @throws InvalidTaskException 
	 */
	@Transactional(readOnly=false)
	public List<Task> updateStatus(ArrayList<Task> checkins) throws InvalidTaskException {
		for(Task task: checkins) {
			if(task.getId() == null || task.getStatus()==null) {
				throw new InvalidTaskException("Tarefa inválida.");
			}
			Task t = findById(task.getId());
			t.setStatus(task.getStatus());
			taskRepository.save(t);
		}
		return checkins;
	}
	
}
