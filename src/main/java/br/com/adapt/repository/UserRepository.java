package br.com.adapt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adapt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
}
