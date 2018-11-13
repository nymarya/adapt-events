package br.com.adapt.service;

import br.com.adapt.exception.InvalidTaskException;
import br.com.adapt.model.Resource;
import br.com.adapt.model.Scheduler;
import br.com.adapt.model.Task;
import br.com.adapt.repository.ResourceRepository;
import br.com.adapt.repository.TaskRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.domain.Status;
import br.com.adapt.domain.Type;


public class TaskService extends ResourceService {

	@Autowired
	private ResourceRepository resourceRepository;
	
	
	/*@Override
	public Task saveTask(Resource entity, Scheduler scheduler) throws InvalidTaskException {
		
		Task task = (Task) entity;
		
		task.setScheduler(scheduler);
		task.setStatus(Status.TODO);
        //Titulo, descrição e dificuldade sempre são obrigatorios
        if( task.getDificulty() == null || task.getTitle().isEmpty() || task.getDescription().isEmpty()) {
        	throw new InvalidTaskException("Tarefa inválida.");
        } 
        
        //Se for rotina, os campos dia, hora de inicio e fim são obrigatorios
        if( task.getType() == Type.ROUTINE) {
        	Integer day = task.getDay();
        	if(task.getEndDate() == null || task.getStartDate() == null || day == null) {
        		throw new InvalidTaskException("Tarefa inválida.");
        	}
        } else { //Se não for rotina, prioridade e tempo esperado são obrigatorios
        	Integer expectedTime = task.getExpectedTime();
        	if(task.getPriority() == null || expectedTime == 0) {
        		throw new InvalidTaskException("Tarefa inválida.");
        	}
        	task.setStartDate(null);
        	task.setEndDate(null);
        }
        // Se não 
		return resourceRepository.save(task);
		
	}*/

	
	
}
