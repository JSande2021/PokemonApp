//package com.project.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.project.data.UsersDataService;
//
//@Service
//public class SecurityBusinessService 
//{
//	@Autowired
//	private UsersDataService service;
//	
//	/*
//	 * Checks to see if the username is in the database by calling the data service
//	 */
//	public boolean authenticate(String username, String password) 
//	{
//		if(service.validate(username, password)) 
//		{
//			System.out.println(String.format("Hello from the SecurityBusinessService username of %s and a password of %s",
//				username, password));
//			return true;
//		}
//		else
//		{
//			System.out.println("Incorrect username or password");
//			return false;
//		}
//	}
//
//}
