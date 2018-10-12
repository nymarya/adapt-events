package br.com.adapt.service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.domain.Type;
import br.com.adapt.exception.InvalidTaskException;
import br.com.adapt.model.Scheduler;
import br.com.adapt.model.Tag;
import br.com.adapt.model.Task;
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
		return taskRepository.save(entity);
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

	public List<Task> findByUserEmail(String name) {
		List<Task> tasks= taskRepository.findByUserEmail(name);
		return tasks;
	}
	
}
