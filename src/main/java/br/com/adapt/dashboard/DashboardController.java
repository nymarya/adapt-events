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

import br.com.adapt.model.Tag;
import br.com.adapt.model.User;
import br.com.adapt.service.SchedulerService;


@Controller
public class DashboardController {

	
	@Autowired
	private SchedulerService SchedulerService;
	
	@GetMapping("/dashboard")
	public String dashboardIndex() {
        return "dashboard/calendar";
    }
	
	@GetMapping("/scheduler")
	public String index(Model model) {
		SortedMap<Date, Date> blocks = SchedulerService.generate();
		model.addAttribute("freeblocks", blocks);
        return "dashboard/calendar";
    }
	
	
	@GetMapping("/task-edit")
	public String taskEdit() {
        return "dashboard/task_edit_form";
    }
	

	
	
}
