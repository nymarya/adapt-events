package br.com.adapt.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.adapt.application.model.Course;
import br.com.adapt.application.service.TaskService;
import br.com.adapt.framework.controller.ResourceRestController;

@RestController
public class TaskRestController extends ResourceRestController<Course> {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/api/tasks")
    public List<Course> resources(@RequestParam(value="start", defaultValue="World") String start,
    		@RequestParam(value="end", defaultValue="World") String end,
    		@RequestParam(value="_", defaultValue="World") String e) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return taskService.findByUserEmail(auth.getName());
    }

}
