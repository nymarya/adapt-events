package br.com.adapt.repository;

import org.springframework.stereotype.Repository;

import br.com.adapt.model.Tag;
import br.com.adapt.model.Resource;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
	@Query("select u from Resource u where u.id = ?1")
	Resource findById(int id);

	@Query("select t.startDate,t.endDate,t.title,t.day,t.id,t.type from Resource t left join Scheduler s on t.scheduler=s.id left join User u on u.id=s.user where u.email = ?1")
	List<Resource> findByUserEmail(String name);

	@Query("select t from Resource t")
	List<Resource> findByUserEmailBetween(String name, LocalDateTime startTime, LocalTime endTime);
	
	@Query("select t from Resource t left join Scheduler s on t.scheduler=s.id left join User u on u.id=s.user where u.email = ?1 and t.type=1")
	List<Resource> findTemporaryByUserEmail(String name);
	
	@Query("select t from Resource t left join Scheduler s on t.scheduler=s.id left join User u on u.id=s.user where u.email = ?1 and t.type=0")
	List<Resource> findRoutineByUserEmail(String name);

	@Query("select t from Resource t left join Scheduler s on t.scheduler=s.id left join User u on u.id=s.user where u.email = ?1 and t.type=1 and t.status <> 2")
	List<Resource> findTemporaryNotDoneByUserAuthenticated(String name);
}