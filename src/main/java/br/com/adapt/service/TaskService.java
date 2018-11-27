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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.domain.Status;
import br.com.adapt.domain.Type;


@Service
@Transactional(readOnly = false)
public class TaskService extends ResourceService<Task> {

	
	@Autowired
	private ResourceRepository<Task> taskRepository;
	
	@Transactional(readOnly = true)
	public Task findById(int id) {
		return taskRepository.findById(id);
	}
	
	
	@Override
	public Task save( Task entity ) {
		

        //Titulo, descrição e dificuldade sempre são obrigatorios
        if( entity.getDificulty() == null || entity.getTitle().isEmpty() || entity.getDescription().isEmpty()) {
        	//throw new InvalidTaskException("Tarefa inválida.");
        } 
        
        //Se for rotina, os campos dia, hora de inicio e fim são obrigatorios
        if( entity.getType() == Type.ROUTINE) {
        	Integer day = entity.getDay();
        	if(entity.getEndDate() == null || entity.getStartDate() == null || day == null) {
        		//throw new InvalidTaskException("Tarefa inválida.");
        	}
        } else { //Se não for rotina, prioridade e tempo esperado são obrigatorios
        	Integer expectedTime = entity.getExpectedTime();
        	if(entity.getPriority() == null || expectedTime == 0) {
        		//throw new InvalidTaskException("Tarefa inválida.");
        	}
        	entity.setStartDate(null);
        	entity.setEndDate(null);
        }	
        
        return entity;

	}

	@Override
	public Task saveResource(Task entity, Scheduler scheduler) throws InvalidTaskException {
        entity.setScheduler(scheduler);
        entity.setStatus(Status.TODO);
        
        save( entity );
         
		return taskRepository.save(entity);
	}



	@Override
	public Resource saveTask(Resource entity, Scheduler scheduler) throws InvalidTaskException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Task saveResource(Task entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Remove tarefa
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(Task entity) {
		taskRepository.delete(entity);		
	}


	@Override
	public List<Task> findByUserEmail(String name) {
		return taskRepository.findByUserEmail(name);
	}

	
	@Override
	public List<Task> findTemporaryNotDoneByUserAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return taskRepository.findTemporaryNotDoneByUserAuthenticated(auth.getName());
	}


	@Override
	public List<Task> findRoutineByUserAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return taskRepository.findRoutineByUserEmail(auth.getName());
	}

	

	
	
}
