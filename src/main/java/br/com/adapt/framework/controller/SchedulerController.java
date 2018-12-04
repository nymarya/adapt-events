package br.com.adapt.framework.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import br.com.adapt.application.model.Course;
import br.com.adapt.application.service.SchedulerCourseService;
import br.com.adapt.application.service.CourseService;
import br.com.adapt.framework.exception.InvalidTaskException;
import br.com.adapt.framework.model.Freeblock;
import br.com.adapt.framework.model.Resource;
import br.com.adapt.framework.model.User;
import br.com.adapt.framework.service.ResourceService;
import br.com.adapt.framework.service.SchedulerService;
import br.com.adapt.framework.util.CheckinForm;
import br.com.adapt.framework.util.CheckinForms;


@Controller
public abstract class SchedulerController {

	
	
	private ResourceService resourceService;
	private CourseService courseService;
	
	@Autowired
	private SchedulerService schedulerService;
	
	
	public abstract String dashboardIndex(Model model);

	
	@GetMapping("/task-edit")
	public String taskEdit() {
        return "dashboard/task_edit_form";
    }
	
	@GetMapping("/download")
	public String download(HttpServletRequest request, HttpServletResponse response) throws Exception{
		schedulerService.download(request, response);
		return "dashboard/profileEdit";
	}

	
	/*
	@GetMapping("/checkin")
	public String checkin(Model model) {
		List<Resource> all = taskService.findTemporaryByUserAuthenticated();
		if(all.isEmpty()) {
			return "redirect:/dashboard";
		}
		CheckinForms c = new CheckinForms((ArrayList<Resource>) all);
		model.addAttribute("tasks", c);
        return "dashboard/checkin";
    }
	
	@PostMapping("/checkin/save")
	public String checkinUpdate(@Valid @ModelAttribute CheckinForms entity, BindingResult result) {
		try {
			List<Resource> all = resourceService.updateStatus(entity.getCheckins());
			
		} catch (InvalidTaskException e) {
			throw new ServiceException(e.getMessage());
		}
		
        return "redirect:/dashboard";
    }*/
}
