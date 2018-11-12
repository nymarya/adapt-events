package br.com.adapt.service;

import br.com.adapt.exception.InvalidTaskException;
import br.com.adapt.model.Resource;
import br.com.adapt.model.Scheduler;
import br.com.adapt.model.Task;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.domain.Status;
import br.com.adapt.domain.Type;


@Service
@Transactional(readOnly = false)
public class TaskService extends ResourceService {

	@Override
	public Resource save( Resource entity ) {
		
		Task ent = (Task)entity;
        
        //Titulo, descrição e dificuldade sempre são obrigatorios
        if( ent.getDificulty() == null || ent.getTitle().isEmpty() || ent.getDescription().isEmpty()) {
        	//throw new InvalidTaskException("Tarefa inválida.");
        } 
        
        //Se for rotina, os campos dia, hora de inicio e fim são obrigatorios
        if( ent.getType() == Type.ROUTINE) {
        	Integer day = entity.getDay();
        	if(ent.getEndDate() == null || entity.getStartDate() == null || day == null) {
        		//throw new InvalidTaskException("Tarefa inválida.");
        	}
        } else { //Se não for rotina, prioridade e tempo esperado são obrigatorios
        	Integer expectedTime = ent.getExpectedTime();
        	if(ent.getPriority() == null || expectedTime == 0) {
        		//throw new InvalidTaskException("Tarefa inválida.");
        	}
        	entity.setStartDate(null);
        	entity.setEndDate(null);
        }	
        
        return ent;

	}

	
	
}
