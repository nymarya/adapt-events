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

import br.com.adapt.application.model.Event;
import br.com.adapt.application.service.EventService;
import br.com.adapt.framework.controller.ResourceController;
import br.com.adapt.framework.model.Resource;
import br.com.adapt.framework.model.User;
import br.com.adapt.framework.service.UserService;


@Controller
public class EventController extends ResourceController<Event> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;
	
	private static final String MSG_SUCESS_INSERT = "Evento cadastrado com sucesso.";
	private static final String MSG_SUCESS_UPDATE = "Evento atualizado com sucesso.";
	private static final String MSG_SUCESS_DELETE = "Evento removido com sucesso.";
	private static final String MSG_ERROR = "Error.";

	@GetMapping("/events")
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmailAdress(auth.getName());
		List<Resource> all = user.getScheduler().getResources();
		model.addAttribute("events", all);
        return "events/index";
    }
	
	@GetMapping("/events/create")
	public String create(Model m) {
		m.addAttribute("endDate", LocalTime.NOON);
		m.addAttribute("startDate", LocalTime.NOON);
        return "events/create";
    }

	@PostMapping("/events/save")
	@Override
	public String store(Event entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Event resource = null;
		
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByEmailAdress(auth.getName());
			resource = eventService.saveResource(entity, user.getScheduler());
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/events/create";
		}
		return "redirect:/events";
	}

	@Override
	@RequestMapping(value = "/events/{id}", method = RequestMethod.POST)
	public String update(Event entity, BindingResult result, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Resource resource = null;
		try {
			entity.setId(id);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByEmailAdress(auth.getName());
			resource = eventService.saveResource(entity, user.getScheduler());
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/events";
	}

	@GetMapping("events/{id}/edit")
	public String edit(Model model, @PathVariable("id") Integer id) {
		
		try {
			if (id != null) {
				
				Event entityTask = eventService.findById(id);
				model.addAttribute("event", entityTask);
				
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "events/edit";
	}

	
	@GetMapping("events/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		
		try {
			if (id != null) {
				
				Resource entity = eventService.findById(id);
				model.addAttribute("event", entity);
				
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "events/show";
	}
	
	@PostMapping("events/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Event entity = eventService.findById(id);
				eventService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/events";
	}
	
	
}
