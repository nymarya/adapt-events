package br.com.adapt.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.adapt.framework.model.User;
import br.com.adapt.framework.repository.UserRepository;
import br.com.adapt.framework.util.AdaptUserDetails;

@Service
public class AdaptUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
    
    public AdaptUserDetailsService() {
        super();
    }
 
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmailAddress(username);
        if (user == null) {
            throw new RuntimeException(username);
        }
        return new AdaptUserDetails(user);
    }
}
