/**
 * 
 */
package br.com.adapt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.exception.InvalidUserException;
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
	
	@Autowired
	private SchedulerService schedulerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Transactional(readOnly = false)
	public User save(User entity) throws InvalidUserException {
		final User user = new User();
		user.setPassword(passwordEncoder.encode(entity.getPassword()));
		user.setName(entity.getName());
		user.setEmail(entity.getEmail());
		if( entity.getEmail().isEmpty() || entity.getName().isEmpty() || entity.getPassword().isEmpty()) {
			throw new InvalidUserException("Usuário inválido.");
		}
		
		// Ao criar um usuario, deve ser criado um
		// scheduler pra ele
		User userTemp =  userRepository.save(user);
		schedulerService.save(userTemp);
		return userTemp;
	}
	
	public User autenticate(String emailAddress) {
		User usuario = userRepository.findByEmailAddress(emailAddress);
		if (usuario == null) {
			throw new RuntimeException("Usuário não cadastrado!");
		}
		return usuario;
	}
	
	public User findByEmailAdress( String emailAddress) {
		User usuario = userRepository.findByEmailAddress(emailAddress);
		if (usuario == null) {
			throw new RuntimeException("Usuário não encontrado!");
		}
		return usuario;
	}
	
	
}
