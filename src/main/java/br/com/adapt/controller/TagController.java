/**
 * 
 */
package br.com.adapt.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.adapt.model.Tag;
import br.com.adapt.model.User;
import br.com.adapt.service.TagService;
import br.com.adapt.service.UserService;

/**
 * @author mayra
 *
 */
@Controller
public class TagController {
	
	private static final String MSG_SUCESS_INSERT = "Tag inserted successfully.";
	private static final String MSG_SUCESS_UPDATE = "Tag successfully changed.";
	private static final String MSG_SUCESS_DELETE = "Deleted tag successfully.";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private TagService tagService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/tags")
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmailAdress(auth.getName());
		List<Tag> all = user.getScheduler().getTags();
		model.addAttribute("tags", all);
        return "tags/index";
    }

	@GetMapping("/tags/create")
	public String create(Model model) {
        return "tags/create";
    }
	
	@PostMapping("/tags")
	public String store( @Valid @ModelAttribute Tag entityTag,BindingResult result, RedirectAttributes redirectAttributes) {
		Tag tag = null;
		
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByEmailAdress(auth.getName());
			tag = tagService.save(entityTag, user.getScheduler());
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/tags/create";
		}
		return "redirect:/tags";
	}
	
	@GetMapping("tags/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		
		try {
			if (id != null) {
				
				Tag entity = tagService.findById(id);
				model.addAttribute("tag", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "tags/edit";
	}
	
	@RequestMapping(value = "/tags/{id}", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute Tag entity, BindingResult result, @PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		Tag tag = null;
		try {
			entity.setId(id);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findByEmailAdress(auth.getName());
			tag = tagService.save(entity, user.getScheduler());
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/tags";
	}
	
	@GetMapping("tags/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		
		try {
			if (id != null) {
				
				Tag entity = tagService.findById(id);
				model.addAttribute("tag", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "tags/show";
	}
	
	@PostMapping("tags/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Tag entity = tagService.findById(id);
				tagService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/tags";
	}

	
}
