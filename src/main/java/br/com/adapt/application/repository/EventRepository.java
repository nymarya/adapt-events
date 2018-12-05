package br.com.adapt.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import br.com.adapt.application.model.Event;
import br.com.adapt.framework.repository.ResourceRepository;

@NoRepositoryBean
public interface EventRepository extends ResourceRepository<Event> {
	@Query("select t from Resource t left join Scheduler s on t.scheduler=s.id left join User u on u.id=s.user where u.email = ?1 and t.postponed=true")
	List<Event> findTemporaryNotPostponedByUserEmail(String name);
}


