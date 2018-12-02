package br.com.adapt.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/profile")
	public String showProfile() {
        return "dashboard/profile";
    }
	
	@GetMapping("/profile-edit")
	public String editProfile(){
		return "dashboard/profileEdit";
	}
	
}
