package br.com.adapt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adapt.model.Resource;
import br.com.adapt.model.Task;
import br.com.adapt.model.User;
import br.com.adapt.service.TaskService;
import br.com.adapt.service.UserService;


@Controller
public class TaskController<T extends Task> extends ResourceController<T> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	
	
	private static final String MSG_SUCESS_INSERT = "Tarefa cadastrada com sucesso.";
	private static final String MSG_SUCESS_UPDATE = "Tarefa atualizada com sucesso.";
	private static final String MSG_SUCESS_DELETE = "Tarefa removida com sucesso.";
	private static final String MSG_ERROR = "Error.";

	

	@Override
	public String store(T entityTask, BindingResult result, RedirectAttributes redirectAttributes) {
		Resource resource = null;
		
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
	public String update(T entity, BindingResult result, Integer id, RedirectAttributes redirectAttributes) {
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

	
	
}
