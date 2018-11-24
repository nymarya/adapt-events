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

import br.com.adapt.domain.Status;
import br.com.adapt.domain.Type;
import br.com.adapt.exception.InvalidTaskException;
import br.com.adapt.model.Scheduler;
import br.com.adapt.model.Tag;
import br.com.adapt.model.Task;
import br.com.adapt.model.Resource;
import br.com.adapt.model.User;
import br.com.adapt.repository.ResourceRepository;


/**
 * @author Jaine
 *
 */
@Service
@Transactional(readOnly = true)
public abstract class ResourceService<T extends Resource> {
	

	@Autowired
	private ResourceRepository resourceRepository;

	@Transactional(readOnly = false)
	public abstract T saveResource(T entity);
	

	@Transactional(readOnly = false)
	public abstract Resource saveTask(Resource entity, Scheduler scheduler) throws InvalidTaskException;

	public abstract T save( T entity );
	
	
	@Transactional(readOnly = false)
	public abstract T saveResource(T entity, Scheduler scheduler) throws InvalidTaskException;
	
	
	@Transactional
	public abstract T findById(int id);
	
	@Transactional(readOnly=false)
	public abstract void delete(T entity);

	/**
	 * Recupera todas as tarefas do usuário logado
	 * @param name
	 * @return
	 */
	public abstract List<T> findByUserEmail(String name);
	
	/**
	 * recupera todas as tarefas temporarias do usuario logado
	 * @return
	 **/
	 
	public List<Resource> findTemporaryByUserAuthenticated(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Resource> resources = resourceRepository.findTemporaryByUserEmail(auth.getName());
		return resources;
	}

	/**
	 * recupera todas as tarefas temporarias do usuario logado
	 * @return
	 */
	public List<Resource> findRoutineByUserAuthenticated(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Resource> resources = resourceRepository.findRoutineByUserEmail(auth.getName());
		return resources;
	}
	
	
	public void teste() {
		System.out.println("oi");
	}

	public List<Resource> findTemporaryNotDoneByUserAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return resourceRepository.findTemporaryNotDoneByUserAuthenticated(auth.getName());
	}
	
	
	/**
	 * Atualiza os status das tarefas após o check-in
	 * @param checkins
	 * @return
	 * @throws InvalidTaskException 
	 */
	@Transactional(readOnly=false)
	public List<Resource> updateStatus(ArrayList<Resource> checkins) throws InvalidTaskException {
		for(Resource resource: checkins) {
			if(resource.getId() == null || resource.getStatus()==null) {
				throw new InvalidTaskException("Tarefa inválida.");
			}
			Resource t = findById(resource.getId());
			t.setStatus(resource.getStatus());
			//resourceRepository.save(t);
		}
		return checkins;
	}

	
}
