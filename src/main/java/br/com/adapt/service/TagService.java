/**
 * 
 */
package br.com.adapt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.repository.TagRepository;
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
	
	

}
