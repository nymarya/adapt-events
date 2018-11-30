package br.com.adapt.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.adapt.application.service.SchedulerTaskService;
import br.com.adapt.framework.controller.SchedulerController;
import br.com.adapt.framework.model.Freeblock;


@Controller
public class SchedulerTaskController extends SchedulerController {

	@Autowired
	protected SchedulerTaskService SchedulerService;
	

	@GetMapping("/dashboard")
	public String dashboardIndex(Model model) {
		
		List< List< Freeblock > > tasks = SchedulerService.generate();
		model.addAttribute("tasks", tasks);
        return "dashboard/calendar";
    }
	
}
