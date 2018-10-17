package br.com.adapt.dashboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.adapt.exception.InvalidTaskException;
import br.com.adapt.model.Freeblock;
import br.com.adapt.model.Tag;
import br.com.adapt.model.Task;
import br.com.adapt.model.User;
import br.com.adapt.service.SchedulerService;
import br.com.adapt.service.TaskService;
import br.com.adapt.util.CheckinForm;
import br.com.adapt.util.CheckinForms;


@Controller
public class DashboardController {

	
	@Autowired
	private SchedulerService SchedulerService;
	
	@Autowired
	private TaskService taskService;
	
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
	
	@GetMapping("/checkin")
	public String checkin(Model model) {
		List<Task> all = taskService.findTemporaryByUserAuthenticated();
		CheckinForms c = new CheckinForms((ArrayList<Task>) all);
		model.addAttribute("tasks", c);
        return "dashboard/checkin";
    }
	
	@PostMapping("/checkin/save")
	public String checkinUpdate(@Valid @ModelAttribute CheckinForms entity, BindingResult result) {
		try {
			List<Task> all = taskService.updateStatus(entity.getCheckins());
		} catch (InvalidTaskException e) {
			throw new ServiceException(e.getMessage());
		}
		
        return "redirect:/dashboard";
    }
}
