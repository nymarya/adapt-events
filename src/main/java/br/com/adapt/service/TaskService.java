package br.com.adapt.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.adapt.model.Scheduler;
import br.com.adapt.model.Tag;
import br.com.adapt.model.Task;
import br.com.adapt.repository.TaskRepository;


/**
 * @author Jaine
 *
 */
@Service
@Transactional(readOnly = true)
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Transactional(readOnly = false)
	public Task saveTask(Task entity) {
		return taskRepository.save(entity);
	}
	
	@Transactional(readOnly = false)
	public Task saveTask(Task entity, Scheduler scheduler) {
        entity.setScheduler(scheduler);
		return taskRepository.save(entity);
	}
	
	@Transactional
	public Task findById(int id) {
		return taskRepository.findById(id);
	}
	
	@Transactional(readOnly=false)
	public void delete(Task entity) {
		taskRepository.delete(entity);
	}

	public List<Task> findByUserEmail(String name) {
		//DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
		//LocalDateTime timePoint = LocalDateTime.now(/* tz */).with(TemporalAdjusters.previousOrSame(firstDayOfWeek));; 
		List<Task> tasks= taskRepository.findByUserEmail(name);
		return tasks;
	}
	
}
