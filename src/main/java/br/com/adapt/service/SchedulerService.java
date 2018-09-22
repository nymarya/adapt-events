/**
 * 
 */
package br.com.adapt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.model.Scheduler;
import br.com.adapt.repository.SchedulerRepository;

/**
 * @author mayra
 *
 */
@Service
@Transactional(readOnly = true)
public class SchedulerService {
	@Autowired
	private SchedulerRepository schedulerRepository;
	
	
	@Transactional(readOnly = false)
	public Scheduler save(Scheduler entity) {
		return schedulerRepository.save(entity);
	}
}
