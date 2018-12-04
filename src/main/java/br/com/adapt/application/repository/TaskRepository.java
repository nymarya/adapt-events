package br.com.adapt.application.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import br.com.adapt.application.model.Task;
import br.com.adapt.framework.repository.ResourceRepository;

@NoRepositoryBean
public interface TaskRepository extends ResourceRepository<Task> {
	
}


