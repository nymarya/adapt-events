package br.com.adapt.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adapt.model.Tag;
import br.com.adapt.model.Task;
import br.com.adapt.model.User;
import br.com.adapt.service.TaskService;
import br.com.adapt.service.UserService;



/**
 * @author mayra
 *
 */

@RestController
public class TaskRestController {

	
	private static final String MSG_SUCESS_INSERT = "Tag inserted successfully.";
	private static final String MSG_SUCESS_UPDATE = "Tag successfully changed.";
	private static final String MSG_SUCESS_DELETE = "Deleted tag successfully.";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;

    @GetMapping("/api/tasks")
    public List<Task> tasks(@RequestParam(value="start", defaultValue="World") String start,
    		@RequestParam(value="end", defaultValue="World") String end,
    		@RequestParam(value="_", defaultValue="World") String e) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return taskService.findByUserEmail(auth.getName());
    }
}