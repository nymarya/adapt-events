package br.com.adapt.framework.controller;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adapt.framework.service.ResourceService;
import br.com.adapt.framework.service.UserService;



/**
 * @author mayra
 *
 */

@Controller
public abstract class ResourceController<T> {

	
	private static final String MSG_SUCESS_INSERT = "Tarefa cadastrada com sucesso.";
	private static final String MSG_SUCESS_UPDATE = "Tarefa atualizada com sucesso.";
	private static final String MSG_SUCESS_DELETE = "Tarefa removida com sucesso.";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private UserService userService;
	
	private ResourceService taskService;
	
	/**
	 * Lista recursos
	 * @param model
	 * @return
	 */
	public abstract String index(Model model);
	
	/**
	 * Renderiza formulário de criação
	 * @param m
	 * @return
	 */
	public abstract String create(Model m);
	
	/**
	 * Método para salvar um novo recurso no BD 
	 * @param entityTask Entidade com infos a ser salvas
	 */
	public abstract String store( @Valid @ModelAttribute T entityTask,BindingResult result, RedirectAttributes redirectAttributes);

	
	/**
	 * Método para atualizar recurso no BD 
	 * @param entityTask Entidade com infos a ser salvas
	 */
	public abstract String update(@Valid @ModelAttribute T entity, BindingResult result, @PathVariable("id") Integer id,RedirectAttributes redirectAttributes);
	
	/**
	 * Exibe formulário para alterar recurso
	 * @param model
	 * @param id
	 * @return
	 */
	public abstract String edit(Model model, @PathVariable("id") Integer id);
	
	/**
	 * Exibe tela com detalhes sobre o recurso
	 * @param model
	 * @param id
	 * @return
	 */
	public abstract String show(Model model, @PathVariable("id") Integer id);
	
	/**
	 * Remove recurso.
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	public abstract String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes);
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(LocalTime.class, new CustomDateEditor(new SimpleDateFormat("H:m"), true));
	}
}