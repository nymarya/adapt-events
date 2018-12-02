package br.com.adapt.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adapt.framework.model.Scheduler;

@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, Integer> {
	
}