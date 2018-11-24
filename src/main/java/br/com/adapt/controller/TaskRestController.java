package br.com.adapt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.adapt.model.Task;
import br.com.adapt.service.TaskService;

@RestController
public class TaskRestController extends ResourceRestController<Task> {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/api/tasks")
    public List<Task> resources(@RequestParam(value="start", defaultValue="World") String start,
    		@RequestParam(value="end", defaultValue="World") String end,
    		@RequestParam(value="_", defaultValue="World") String e) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return taskService.findByUserEmail(auth.getName());
    }

}
