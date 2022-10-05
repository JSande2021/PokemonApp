package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.model.Card;

@Controller
@RequestMapping("/")
public class IndexController 
{	
	/*
	 * gets /index mapping and returns the index page along with new attributes
	 */
	@GetMapping("/index")
	public String index(Model model) 
	{
		//Create the REST API end point URL
				String hostname = "localhost";
				int port = 8082;
				
				//Get all the cards in the database from the REST API
				String url = "http://" + hostname + ":" + port + "/service/cards";
				RestTemplate restTemplate = new RestTemplate();
				List<Card> cards = new ArrayList();
				//HttpEntity<Card> httpEntity = new HttpEntity<Card>();
				try {
				ResponseEntity<List<Card>> rateResponse = restTemplate.exchange(url,  HttpMethod.GET, null, new ParameterizedTypeReference<List<Card>>() {});
				cards = rateResponse.getBody();
				} catch(RestClientException e) {
					 ResponseEntity.status(HttpStatus.CONFLICT).body("Could not connect");
				}
				//Display the cards on the index page
				model.addAttribute("title", "List of Cards");
				model.addAttribute("cards", cards);
				
				return "index";				
	}
}
