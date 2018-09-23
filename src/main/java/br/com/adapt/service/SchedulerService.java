/**
 * 
 */
package br.com.adapt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.model.Scheduler;
import br.com.adapt.model.User;
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
	
	/**
	 * Ao criar um usuário, deve ser criado um 
	 * scheduler paar ele
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public Scheduler save(User user) {
		final Scheduler scheduler = new Scheduler();
		scheduler.setUser(user);
		return schedulerRepository.save(scheduler);
	}
}
