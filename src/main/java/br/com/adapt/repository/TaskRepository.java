package br.com.adapt.repository;

import org.springframework.stereotype.Repository;
import br.com.adapt.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
}