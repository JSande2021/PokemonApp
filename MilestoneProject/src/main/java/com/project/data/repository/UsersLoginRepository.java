package com.project.data.repository;



import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.project.data.entity.UserLoginEntity;

public interface UsersLoginRepository extends MongoRepository<UserLoginEntity, String>
{
	UserLoginEntity findByUsername(String username);
	
	//Returns a the user document in a String
	public List<String> getUserCardsById(String id);
}
