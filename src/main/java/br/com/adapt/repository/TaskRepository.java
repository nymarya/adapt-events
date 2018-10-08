package br.com.adapt.repository;

import org.springframework.stereotype.Repository;

import br.com.adapt.model.Tag;
import br.com.adapt.model.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Query("select u from Task u where u.id = ?1")
	Task findById(int id);

	@Query("select t from Task t left join Scheduler s on t.scheduler=s.id left join User u on u.id=s.user where u.email = ?1")
	List<Task> findByUserEmail(String name);
}