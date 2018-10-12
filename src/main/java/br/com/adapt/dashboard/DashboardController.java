package br.com.adapt.dashboard;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.adapt.model.Freeblock;
import br.com.adapt.model.Tag;
import br.com.adapt.model.Task;
import br.com.adapt.model.User;
import br.com.adapt.service.SchedulerService;


@Controller
public class DashboardController {

	
	@Autowired
	private SchedulerService SchedulerService;
	
	@GetMapping("/dashboard")
	public String dashboardIndex(Model model) {
		
		List< List< Freeblock > > tasks = SchedulerService.generate();
		model.addAttribute("tasks", tasks);
        return "dashboard/calendar";
    }
	
	@GetMapping("/scheduler")
	public String index(Model model) {

		List< List< Freeblock > > tasks = SchedulerService.generate();
		model.addAttribute("tasks", tasks);
        return "dashboard/calendar";
    }
	
	
	@GetMapping("/task-edit")
	public String taskEdit() {
        return "dashboard/task_edit_form";
    }
	

	
	
}
