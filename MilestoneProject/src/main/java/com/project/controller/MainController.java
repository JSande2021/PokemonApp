package com.project.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.model.LoginModel;
//import com.project.service.SecurityBusinessService;

@Controller
@RequestMapping("")
public class MainController {	
	
//	@SuppressWarnings("unused")
//	@Autowired
//	private SecurityBusinessService security;
	
	/*
	 * Gets root mapping and return home page view
	 */
	@GetMapping("/")
	public String HomeController(Model model) {		
		
		//Add the login model for the login form in the navbar
		model.addAttribute("loginModel", new LoginModel());		
		
		
		return "main";
	}
	
	/*
	 * Gets /about mapping and return the about view
	 */
	@GetMapping("/about")
	public String AboutPage(Model model) {
		
		//sets message
		model.addAttribute("message", "About Pokemon Card Book");
		
		//Add the login model for the login form in the navbar
		model.addAttribute("loginModel", new LoginModel());
		model.addAttribute("footer", new BCryptPasswordEncoder().encode("test"));
		
		return "about";

	}
}
