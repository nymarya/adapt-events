package br.com.adapt.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import br.com.adapt.model.Task;

@NoRepositoryBean
public interface TaskRepository extends ResourceRepository<Task> {
	
}


