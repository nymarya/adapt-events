package br.com.adapt.repository;

import org.springframework.stereotype.Repository;

import br.com.adapt.model.Tag;
import br.com.adapt.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Query("select u from Task u where u.id = ?1")
	Task findById(int id);
}