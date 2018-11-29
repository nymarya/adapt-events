package br.com.adapt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.adapt.service.SharedScheduleByDownload;

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
