package com.project.api;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * Takes the card id(s) in the url and returns the cards in the database associated with it/them
	 */
	@GetMapping(path ="/getcard/{ids}")
	public ResponseEntity<?> getCard(@PathVariable("ids") String ids) 
	{
		//Create a list of ids from the url
		List<String> idList = Arrays.asList(ids.split("-"));
		
		if(idList == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		//Create a list of cards that are associated with the ids in the idList
		List<Card> cards = new ArrayList<Card>();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "test");
		try 
		{
			for(String id : idList) 
			{
				cards.add(service.getCardById(id));
			}
			
			return new ResponseEntity<>(cards, HttpStatus.OK);
		}
		catch (Exception e) 
		{
			System.out.println("In the Catch Exception: " + e);
			return new ResponseEntity<>("Internal_Server_Error", headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
