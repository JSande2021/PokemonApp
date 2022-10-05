package com.project.data;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.data.entity.CardEntity;
import com.project.data.repository.CardsRepository;

@Service
public class CardsDataService implements DataAccessInterface<CardEntity> 
{
	@Autowired
	private CardsRepository cardsRepository;
	
	/*
	 * Constructor
	 */
	
	public CardsDataService(CardsRepository cardsRepository)
	{
		
		this.cardsRepository = cardsRepository;
	}

	/*
	 * Returns an array list of all of the cards in the database
	 */
	public List<CardEntity> findAll() 
	{
		List<CardEntity> orders = new ArrayList<CardEntity>();
		
		try {
			Iterable<CardEntity> ordersIterable = cardsRepository.findAll();
			
			orders = new ArrayList<CardEntity>();
			ordersIterable.forEach(orders::add);		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return orders;
	}


	/*
	 * Calls the cards repository save method with the parametized card entity 
	 */
	public boolean create(CardEntity card) 
	{
		try 
		{
			this.cardsRepository.save(card);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	/*
	 * Unimplemented update method
	 */
	public boolean update(CardEntity t) {
		cardsRepository.save(t);
		return true;
	}

	/*
	 * Unimplemented delete method
	 */
	public boolean delete(CardEntity t) {
		cardsRepository.delete(t);
		return true;
	}

	/*
	 * Calls the cards repository's getOrderById function with the parametized id
	 */
	@Override
	public CardEntity findById(String id) {
		return cardsRepository.getCardById(id);
	}	

}

