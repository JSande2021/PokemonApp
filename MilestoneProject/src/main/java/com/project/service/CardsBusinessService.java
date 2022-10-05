package com.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.project.data.CardsDataService;
import com.project.data.entity.CardEntity;
import com.project.model.Card;

public class CardsBusinessService implements CardsBusinessServiceInterface {
	
	@Autowired
	private CardsDataService service;
	
	/*
	 * Methods that set the data to be retrieved by the controller (will be replaced by actual data in the next implementation) 
	 */
	public List<Card> getCards() {
		//get all the Cards 
		List<CardEntity> cardsEntity = service.findAll();
		
		//Iterate over the Cards and create a list of Domain Orders
		List<Card> cardsDomain = new ArrayList<Card>();
		for(CardEntity entity : cardsEntity) {
			cardsDomain.add(new Card(entity.getId(),entity.getName(), entity.getType(), entity.getImage(),entity.getRareStatus(), entity.getCondition()));
		}
		
		//Return list of Domain Cards
		return cardsDomain;
	}
	
	/*
	 * Calls the data service's findById method
	 */
	public CardEntity findById(String id) {
			return service.findById(id);
	}


	/*
	 * Calls the data service's update method
	 */
	public void delete(CardEntity card) {
		service.delete(card);	
	}
	
	/*
	 * Calls the data service's update method
	 */
	public void createCard(String name, String type, String image, String rareStatus, String condition) {
		// TODO Auto-generated method stub
		service.create(new CardEntity(name, type, image, rareStatus, condition));
	}

	/*
	 * Calls the data service's update method
	 */
	public void updateCard(@Valid Card card) {
		// TODO Auto-generated method stub
		CardEntity eCard = new CardEntity(card.getId(),card.getName(),card.getType(),card.getImage(), card.getRareStatus(), card.getCondition());
		service.update(eCard);
	}

	/*
	 * Calls the data service's update method
	 */
	public void update(CardEntity card) {
		service.update(card);
	}
	
	/*
	 * Called on the initialization of the service
	 */
	@Override
	public void init() {
		System.out.println("CardsBusinessService init method");
		System.out.println(new BCryptPasswordEncoder().encode("test"));
	}
	
	/*
	 * Test method with no implementation
	 */
	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Gets the card entity associated with the given id and returns it as a card model
	 */
	@Override
	public Card getCardById(String id) {
		CardEntity orderEntity = service.findById(id);
		
		return new Card(orderEntity.getId(), orderEntity.getName(), orderEntity.getType(), orderEntity.getImage(), orderEntity.getRareStatus(), orderEntity.getCondition());
	}

	/*
	 * Called on the destruction (end of use) of the service
	 */
	@Override
	public void destroy() {
		System.out.println("CardsBusinessService init method");
		System.out.println(new BCryptPasswordEncoder().encode("test"));		
	}
}
