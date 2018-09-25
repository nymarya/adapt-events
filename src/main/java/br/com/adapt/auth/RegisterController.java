package br.com.adapt.auth;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.com.adapt.model.User;
import br.com.adapt.service.UserService;

@Controller
public class RegisterController {

	
	private static final String MSG_SUCESS_INSERT = "Student inserted successfully.";
	private static final String MSG_SUCESS_UPDATE = "Student successfully changed.";
	private static final String MSG_SUCESS_DELETE = "Deleted Student successfully.";
	private static final String MSG_ERROR = "Error.";
	
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user-register")
	public String register() {
        return "auth/register";
    }
	
	@PostMapping("/user-register-action")
	public String registerAction( @Valid @ModelAttribute User entityUser, BindingResult result, RedirectAttributes redirectAttributes) {
		
		User user = null;
		
		try {
			user = userService.save(entityUser);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}

		
        return "redirect:/login";
    }
	
}
