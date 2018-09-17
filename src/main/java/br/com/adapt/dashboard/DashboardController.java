package br.com.adapt.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {

	@GetMapping("/dashboard")
	public String dashboardIndex() {
        return "dashboard/calendar";
    }
	
	@GetMapping("/task-register")
	public String taskRegister() {
        return "dashboard/task_form";
    }
	
	@GetMapping("/task-edit")
	public String taskEdit() {
        return "dashboard/task_edit_form";
    }
	
}
