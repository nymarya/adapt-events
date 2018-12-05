package br.com.adapt.application.service;

import br.com.adapt.application.domain.Category;
import br.com.adapt.application.model.Event;
import br.com.adapt.application.repository.EventRepository;
import br.com.adapt.framework.domain.Status;
import br.com.adapt.framework.domain.Type;
import br.com.adapt.framework.exception.InvalidTaskException;
import br.com.adapt.framework.model.Resource;
import br.com.adapt.framework.model.Scheduler;
import br.com.adapt.framework.repository.ResourceRepository;
import br.com.adapt.framework.service.ResourceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = false)
public class EventService extends ResourceService<Event> {

	
	@Autowired
	private ResourceRepository<Event> taskRepository;
	
	@Transactional(readOnly = true)
	public Event findById(int id) {
		return taskRepository.findById(id);
	}
	
	
	@Override
	public Event save( Event entity ) throws InvalidTaskException {
		

        //Titulo, descrição e dificuldade sempre são obrigatorios
        if( entity.getCategory() == null || entity.getTitle().isEmpty() || entity.getDescription().isEmpty()) {
        	throw new InvalidTaskException("Evento inválido.");
        } 
        
        //Se for rotina, os campos dia, hora de inicio e fim são obrigatorios
        if( entity.getType() == Type.ROUTINE) {
        	Integer day = entity.getDay();
        	if(entity.getEndDate() == null || entity.getStartDate() == null || day == null) {
        		throw new InvalidTaskException("Evento inválido.");
        	}
        } else { //Se não for rotina, prioridade e tempo esperado são obrigatorios
        	//Integer expectedTime = entity.getExpectedTime();
        	//if(expectedTime == 0) {
        	//	throw new InvalidTaskException("Evento inválido.");
        	//}
        	entity.setStartDate(null);
        	entity.setEndDate(null);
        	
        	// verifica se qual a categoria da atividade
            if( entity.getCategory() == Category.MINICOURSE ){
            	entity.setExpectedTime(60);
            } else if(entity.getCategory() == Category.TALK ){
            	entity.setExpectedTime(90);
            } else if(entity.getCategory() == Category.WORKSHOP ){
            	entity.setExpectedTime(60);
            }
            
        }	
        
        
        

        
        return entity;

	}

	@Override
	public Event saveResource(Event entity, Scheduler scheduler) throws InvalidTaskException {
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
	public Event saveResource(Event entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Remove tarefa
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(Event entity) {
		taskRepository.delete(entity);		
	}


	@Override
	public List<Event> findByUserEmail(String name) {
		return taskRepository.findByUserEmail(name);
	}

	
	@Override
	public List<Event> findTemporaryNotDoneByUserAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return taskRepository.findTemporaryNotDoneByUserAuthenticated(auth.getName());
	}


	@Override
	public List<Event> findRoutineByUserAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return taskRepository.findRoutineByUserEmail(auth.getName());
	}

	
	
}
