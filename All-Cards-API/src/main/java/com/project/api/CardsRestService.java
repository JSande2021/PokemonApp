package com.project.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Card;
import com.project.service.CardsBusinessService;

@RestController
@RequestMapping("/service")
public class CardsRestService 
{
	@Autowired
	CardsBusinessService service;
	
	/*
	 * Gets all of the cards stored in the database 
	 */
	@GetMapping(path="/cards")
	public ResponseEntity<?> getCards()
	{
		try {
			List<Card> cards = service.getAllCards();
			
			if(cards == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else {
				return new ResponseEntity<>(cards, HttpStatus.OK);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
