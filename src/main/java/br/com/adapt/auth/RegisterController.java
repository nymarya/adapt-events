package br.com.adapt.auth;

import org.springframework.web.bind.annotation.GetMapping;

public class RegisterController {

	@GetMapping("/register")
	public String register() {
        return "auth/register";
    }
	
}
