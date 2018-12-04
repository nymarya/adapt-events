package br.com.adapt.application.controller;

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

import br.com.adapt.application.model.Course;
import br.com.adapt.application.service.CourseService;
import br.com.adapt.framework.controller.ResourceController;
import br.com.adapt.framework.model.Resource;
import br.com.adapt.framework.model.User;
import br.com.adapt.framework.service.UserService;


@Controller
public class CourseController extends ResourceController<Course> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	private static final String MSG_SUCESS_INSERT = "Atividade cadastrada com sucesso.";
	private static final String MSG_SUCESS_UPDATE = "Atividade atualizada com sucesso.";
	private static final String MSG_SUCESS_DELETE = "Atividade removida com sucesso.";
	private static final String MSG_ERROR = "Error.";
	

	@GetMapping("/tasks")
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmailAdress(auth.getName());
		List<Resource> all = user.getScheduler().getResources();
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
	public String store(Course entityTask, BindingResult result, RedirectAttributes redirectAttributes) {
		Course resource = null;
		
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByEmailAdress(auth.getName());
			resource = courseService.saveResource(entityTask, user.getScheduler());
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/tasks/create";
		}
		return "redirect:/tasks";
	}

	@Override
	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.POST)
	public String update(Course entity, BindingResult result, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Resource resource = null;
		try {
			entity.setId(id);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByEmailAdress(auth.getName());
			resource = courseService.saveResource(entity, user.getScheduler());
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
				
				Course entityTask = courseService.findById(id);
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
				
				Resource entity = courseService.findById(id);
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
				Course entity = courseService.findById(id);
				courseService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/tasks";
	}
	
	
}
