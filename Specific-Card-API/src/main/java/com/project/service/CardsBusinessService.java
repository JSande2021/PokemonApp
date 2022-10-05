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
	private CardsRepository cardsRepository;
	
	public CardsBusinessService(CardsRepository cardsRepository) 
	{
		this.cardsRepository = cardsRepository;
	}
	
	/*
	 * Gets the card entities from the repository and converts them to card models
	 */
	public List<Card> getAllCards()
	{
		List<CardEntity> CardsEntity = cardsRepository.findAll();
		
		List<Card> CardsDomain = new ArrayList<Card>();
		for(CardEntity entity : CardsEntity) 
		{
			CardsDomain.add(new Card(entity.getId(),entity.getName(), entity.getType(), entity.getImage(),entity.getRareStatus(), entity.getCondition()));
			
		}
		
		return CardsDomain;
	}
	
	/*
	 * Return the card entity from the repository assigned to an id as a card model
	 */
	public Card getCardById(String id) 
	{		
		CardEntity entity = cardsRepository.getCardById(id);
			
		
		Card card = null;
		
		if(entity != null) 
		{
			card = new Card(entity.getId(),entity.getName(), entity.getType(), entity.getImage(),entity.getRareStatus(), entity.getCondition());
		}
		else 
		{
			System.out.println("Card not found");
		}

		
		return card;
	}

}
