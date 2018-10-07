package br.com.adapt.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adapt.model.Tag;
import br.com.adapt.model.Task;
import br.com.adapt.model.User;
import br.com.adapt.service.TaskService;
import br.com.adapt.service.UserService;



/**
 * @author mayra
 *
 */

@Controller
public class TaskController {

	
	private static final String MSG_SUCESS_INSERT = "Tag inserted successfully.";
	private static final String MSG_SUCESS_UPDATE = "Tag successfully changed.";
	private static final String MSG_SUCESS_DELETE = "Deleted tag successfully.";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/tasks")
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmailAdress(auth.getName());
		List<Task> all = user.getScheduler().getTasks();
		model.addAttribute("tasks", all);
        return "tasks/index";
    }
	
	@GetMapping("/tasks/create")
	public String taskCreate() {
        return "tasks/create";
    }
	
	@PostMapping("/task/save")
	public String store( @Valid @ModelAttribute Task entityTask,BindingResult result, RedirectAttributes redirectAttributes) {
		Task task = null;
		
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByEmailAdress(auth.getName());
			task = taskService.saveTask(entityTask, user.getScheduler());
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/tasks";
	}

	
	@GetMapping("tasks/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		
		try {
			if (id != null) {
				
				Task entityTask = taskService.findById(id);
				model.addAttribute("task", entityTask);
				
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "tasks/edit";
	}
	
	
	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute Task entity, BindingResult result, @PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		Task task = null;
		try {
			entity.setId(id);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByEmailAdress(auth.getName());
			task = taskService.saveTask(entity, user.getScheduler());
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/tasks";
	}
	
	@GetMapping("tasks/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		
		try {
			if (id != null) {
				
				Task entity = taskService.findById(id);
				model.addAttribute("task", entity);
				
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "tasks/show";
	}
	
	@PostMapping("tasks/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Task entity = taskService.findById(id);
				taskService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/tasks";
	}
}