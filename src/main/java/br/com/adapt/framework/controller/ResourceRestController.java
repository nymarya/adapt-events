package br.com.adapt.framework.controller;

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

import br.com.adapt.application.model.Tag;
import br.com.adapt.application.model.Task;
import br.com.adapt.application.service.TaskService;
import br.com.adapt.framework.model.Resource;
import br.com.adapt.framework.model.User;
import br.com.adapt.framework.service.ResourceService;
import br.com.adapt.framework.service.UserService;



/**
 * @author mayra
 *
 */

@RestController
public abstract class ResourceRestController<T> {

	
	private static final String MSG_SUCESS_INSERT = "Tag inserted successfully.";
	private static final String MSG_SUCESS_UPDATE = "Tag successfully changed.";
	private static final String MSG_SUCESS_DELETE = "Deleted tag successfully.";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private UserService userService;
	
	
	private ResourceService resourceService;

	/** Método para recuperar a lista de recursos alocados
	 *  em um periodo
	 */
    public abstract List<T> resources(@RequestParam(value="start", defaultValue="World") String start,
    		@RequestParam(value="end", defaultValue="World") String end,
    		@RequestParam(value="_", defaultValue="World") String e);
    
}