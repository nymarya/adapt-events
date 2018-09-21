/**
 * 
 */
package br.com.adapt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.model.User;
import br.com.adapt.repository.UserRepository;

/**
 * @author jainebudke
 *
 */
@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional(readOnly = false)
	public User save(User entity) {
		return userRepository.save(entity);
	}
	
	
}
