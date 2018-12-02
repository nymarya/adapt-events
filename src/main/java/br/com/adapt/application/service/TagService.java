/**
 * 
 */
package br.com.adapt.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.application.model.Tag;
import br.com.adapt.application.repository.TagRepository;
import br.com.adapt.framework.exception.InvalidTagException;
import br.com.adapt.framework.model.Scheduler;

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
	public Tag save(Tag entity) {
		return tagRepository.save(entity);
	}
	
	@Transactional(readOnly = false)
	public Tag save(Tag entity, Scheduler scheduler) throws InvalidTagException {
		if( entity.getName().isEmpty() || entity.getColor().isEmpty() || scheduler == null) {
			throw new InvalidTagException("Tag inválida.");
		}
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
