package br.com.adapt.controller;

import java.time.LocalTime;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adapt.model.Resource;
import br.com.adapt.model.Task;
import br.com.adapt.model.User;
import br.com.adapt.service.TaskService;
import br.com.adapt.service.UserService;


@Controller
public class TaskController extends ResourceController<Task> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	private static final String MSG_SUCESS_INSERT = "Tarefa cadastrada com sucesso.";
	private static final String MSG_SUCESS_UPDATE = "Tarefa atualizada com sucesso.";
	private static final String MSG_SUCESS_DELETE = "Tarefa removida com sucesso.";
	private static final String MSG_ERROR = "Error.";

	@GetMapping("/tasks")
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmailAdress(auth.getName());
		List<Resource> all = user.getScheduler().getTasks();
		model.addAttribute("tasks", all);
        return "tasks/index";
    }
	
	@GetMapping("/tasks/create")
	public String create(Model m) {
		m.addAttribute("endDate", LocalTime.NOON);
		m.addAttribute("startDate", LocalTime.NOON);
        return "tasks/create";
    }

	@PostMapping("/task/save")
	@Override
	public String store(Task entityTask, BindingResult result, RedirectAttributes redirectAttributes) {
		Task resource = null;
		
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByEmailAdress(auth.getName());
			resource = taskService.saveResource(entityTask, user.getScheduler());
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/tasks/create";
		}
		return "redirect:/tasks";
	}

	@Override
	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.POST)
	public String update(Task entity, BindingResult result, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Resource resource = null;
		try {
			entity.setId(id);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByEmailAdress(auth.getName());
			resource = taskService.saveResource(entity, user.getScheduler());
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/tasks";
	}

	@GetMapping("tasks/{id}/edit")
	public String edit(Model model, @PathVariable("id") Integer id) {
		
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

	
	@GetMapping("tasks/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		
		try {
			if (id != null) {
				
				Resource entity = taskService.findById(id);
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
