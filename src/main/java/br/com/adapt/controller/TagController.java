/**
 * 
 */
package br.com.adapt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mayra
 *
 */
@Controller
public class TagController {

	@GetMapping("/tags/create")
	public String create() {
        return "tag/create";
    }
	
	
}
