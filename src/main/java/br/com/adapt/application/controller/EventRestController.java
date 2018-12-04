package br.com.adapt.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.adapt.application.model.Event;
import br.com.adapt.application.service.EventService;
import br.com.adapt.framework.controller.ResourceRestController;

@RestController
public class EventRestController extends ResourceRestController<Event> {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/api/events")
    public List<Event> resources(@RequestParam(value="start", defaultValue="World") String start,
    		@RequestParam(value="end", defaultValue="World") String end,
    		@RequestParam(value="_", defaultValue="World") String e) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return eventService.findByUserEmail(auth.getName());
    }

}
