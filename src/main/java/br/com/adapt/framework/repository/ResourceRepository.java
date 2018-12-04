package br.com.adapt.framework.repository;

import org.springframework.stereotype.Repository;

import br.com.adapt.application.model.Tag;
import br.com.adapt.application.model.Event;
import br.com.adapt.framework.model.Resource;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository

public interface ResourceRepository<T extends Resource> extends JpaRepository<T, Integer> {

	
	@Query("select u from Resource u where u.id = ?1")
	T findById(int id);

	@Query("select t from Resource t inner join Scheduler s on s.id=t.scheduler where s.user.email = ?1")
	List<T> findByUserEmail(String name);

	@Query("select t from Resource t")
	List<T> findByUserEmailBetween(String name, LocalDateTime startTime, LocalTime endTime);
	
	@Query("select t from Resource t left join Scheduler s on t.scheduler=s.id left join User u on u.id=s.user where u.email = ?1 and t.type=1")
	List<T> findTemporaryByUserEmail(String name);
	
	@Query("select t from Resource t left join Scheduler s on t.scheduler=s.id left join User u on u.id=s.user where u.email = ?1 and t.type=0")
	List<T> findRoutineByUserEmail(String name);
	 
	@Query("select t from Resource t left join Scheduler s on t.scheduler=s.id left join User u on u.id=s.user where u.email = ?1 and t.type=1 and t.status <> 2")
	List<T> findTemporaryNotDoneByUserAuthenticated(String name);
	
}