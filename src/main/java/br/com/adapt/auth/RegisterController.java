package br.com.adapt.auth;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

	@GetMapping("/user-register")
	public String register() {
        return "auth/register";
    }
	
}
