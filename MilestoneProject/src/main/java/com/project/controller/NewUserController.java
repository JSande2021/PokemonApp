package com.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.model.LoginModel;
import com.project.service.UserLoginBusinessService;

@Controller
@RequestMapping("/")
public class NewUserController 
{
	@Autowired
	private UserLoginBusinessService service;
	
	/*
	 * gets /newUser mapping and returns the newUser page along with new attributes
	 */
	@GetMapping("/newUser")
	public String NewUserPage(Model model) {
		LoginModel user = new LoginModel();
		
		//sets new message
		model.addAttribute("message", "New User Registration Form");
		
		//sets new user
		model.addAttribute("User", user);
		return "newUser";
	}
	
	/*
	 * Results from hitting the submit button in the registration form
	 */
	@PostMapping("/saveUser")
	public String doLogin(@Valid LoginModel User, BindingResult bindingResult, Model model, @ModelAttribute(value="username") String username,
			@ModelAttribute(value="password") String password)
	{
		//Check for validation errors
		if(bindingResult.hasErrors()) 
		{
			model.addAttribute("title", "New User Registration Form");
			return "newUser";
		}
		
		//Display the New User Homepage View
		model.addAttribute("title", "New User Created Successfully");
		
		//Add the login model for the login form in the navbar
		model.addAttribute("loginModel", new LoginModel());
		
		//Adds the new user to the database
		service.createUser(username, password);
		
		//Navigate back to the Main View
		return "main";
	}
}
