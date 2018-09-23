/**
 * 
 */
package br.com.adapt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.repository.TagRepository;
import br.com.adapt.model.Scheduler;
import br.com.adapt.model.Tag;

/**
 * @author mayra
 *
 */
@Service
@Transactional(readOnly = true)
public class TagService {
	@Autowired
	private TagRepository tagRepository;
	
	@Transactional(readOnly = false)
	public Tag save(Tag entity, Scheduler scheduler) {
        entity.setScheduler(scheduler);
		return tagRepository.save(entity);
	}
	
	@Transactional
	public Tag findById(int id) {
		return tagRepository.findById(id);
	}
	
	@Transactional(readOnly=false)
	public void delete(Tag entity) {
		tagRepository.delete(entity);
	}

}
