package com.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;






@Controller
public class LoginController
{	
	/*
	 * Gets the mapping for /login and returns the login page
	 */
	@GetMapping("/login")
	public String display(Model model) {
		model.addAttribute("title", "Login Form");		
		return "login";
	}
	
}
