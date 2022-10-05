package com.project.data.repository;


import org.springframework.data.mongodb.repository.MongoRepository;



import com.project.data.entity.CardEntity;

public interface CardsRepository extends MongoRepository<CardEntity, String>
{	
	
	public CardEntity getCardById(String id);
	
}
