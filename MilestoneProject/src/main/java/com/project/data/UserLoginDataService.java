package com.project.data;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.data.entity.CardEntity;
import com.project.data.entity.UserLoginEntity;



import com.project.data.repository.UsersLoginRepository;


@Service
public class UserLoginDataService implements UserLoginDataAccessInterface<UserLoginEntity>, DataAccessInterface<UserLoginEntity>
{
	@Autowired
	private UsersLoginRepository usersRepository; 
	
	/*
	 * Calls the users repository findByUsername method with the parametized username
	 */
	public UserLoginEntity findByUsername(String username)
	{
		return usersRepository.findByUsername(username);
	}

	
	public List<UserLoginEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public CardEntity findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean create(UserLoginEntity t) {
		try 
		{
			this.usersRepository.save(t);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	

	
	public boolean update(UserLoginEntity t) {
		// TODO Auto-generated method stub
		return false;
	}


	public UserLoginEntity FindByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * Calls the repository's getUserCardsById method
	 */
	public List<String> getUserCardsById(String id) {
		return usersRepository.getUserCardsById(id);
	}

	
 }

