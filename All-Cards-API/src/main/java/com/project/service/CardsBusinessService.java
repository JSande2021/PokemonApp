package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.data.entity.CardEntity;
import com.project.data.repository.CardsRepository;
import com.project.model.Card;

@Service
public class CardsBusinessService {
	
	@Autowired
	private CardsRepository CardsRepository;
	
	public CardsBusinessService(CardsRepository CardsRepository) 
	{
		this.CardsRepository = CardsRepository;
	}
	
	/*
	 * Returns all of the card entities from the repository and converts them to card models
	 */
	public List<Card> getAllCards()
	{
		List<CardEntity> CardsEntity = CardsRepository.findAll();
		
		List<Card> CardsDomain = new ArrayList<Card>();
		for(CardEntity entity : CardsEntity) 
		{
			CardsDomain.add(new Card(entity.getId(),entity.getName(), entity.getType(), entity.getImage(),entity.getRareStatus(), entity.getCondition()));
			
		}
		
		return CardsDomain;
	}

}
