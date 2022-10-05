package com.project.service;

import java.util.List;


import com.project.model.Card;

public interface CardsBusinessServiceInterface {
	public List<Card> getCards();
	public void test();	
	public Card getCardById(String id);
	public void init();
	public void destroy();
}
