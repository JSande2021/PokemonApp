package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.data.UserLoginDataService;
import com.project.data.entity.UserLoginEntity;

@Service
public class UserLoginBusinessService implements UserDetailsService {
	@Autowired
	private UserLoginDataService service;
	
	/*
	 * Returns the user associated with a particular username
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		UserLoginEntity user = service.findByUsername(username);
		//If the user exists
		if(user != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("USER"));
			return new User(user.getUsername(),user.getPassword(), authorities);
		}
		//If the user doesn't exist
		else 
		{
			throw new UsernameNotFoundException("username not found");
		}
	}
	
	
	/*
	 * Calls the data service's findByUsername method
	 */
	public UserLoginEntity findByUsername(String username)
	{
		return service.findByUsername(username);
	}
	
	
	/*
	 * Takes the username and password from the create user form and calls the data service create method
	 */
	public void createUser(String username, String password) {
		UserLoginEntity user = new UserLoginEntity(username, password);
		service.create(user);
		
	}
	
	/*
	 * Takes the array of strings sent by the data layer (which only has the first index full) and turns it into one string
	 */
	public String getUserCardsById(String id) {
		List<String> temp = service.getUserCardsById(id);
		return temp.get(0);
	}
	

}
