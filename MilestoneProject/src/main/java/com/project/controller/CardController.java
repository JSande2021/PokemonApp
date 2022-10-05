package com.project.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.data.entity.CardEntity;
import com.project.data.entity.UserLoginEntity;

import com.project.model.Card;
import com.project.model.LoginModel;
import com.project.service.CardsBusinessService;
import com.project.service.UserLoginBusinessService;

@Controller
@RequestMapping("/")
public class CardController {
	//Gets an instance of the CardsBusinessService
	@Autowired
	private CardsBusinessService cardService;
	
	@Autowired
	private UserLoginBusinessService service;

	@PostMapping(value = "/myCards/edit{id}")
	public String showUpdateForm(@RequestParam String id, Model model) {
		model.addAttribute("Card", cardService.findById(id));
		return "edit";
	}

	// @PostMapping(value = { "/Contact/updateContacts/{id}" })
	// public String updateContact(@PathVariable("id") String id, Model model,
	// Contact contact)
	@PostMapping(value = "/myCards/updateCard/{id}")
	public String updateCard(@PathVariable(value = "id") String id, CardEntity Card, Model model) {
		cardService.update(Card);
		// cardService.updateCard(Card);
		return "redirect:/myCards";
	}

	/*
	 * Gets the mapping for /myCards which is only accessible when logged in and
	 * is the default login success mapping for the Spring Security Config	 *
	 */
	@GetMapping("/myCards")
	public String myCards(Model model, Authentication authentication) {
		// Create the REST API end point URL
		String hostname = "localhost";
		int port = 8081;
		
		//get the user currently logged in
		UserLoginEntity user = service.findByUsername(authentication.getName());
		
		//Get the id of that user
		String userString = user.getId();
		
		//Get the card ids associated with that user
		String temp = service.getUserCardsById(userString);
		
		//Build an array of strings based off of the cards ids at the end of the userString
		String temp2 = temp.substring((temp.indexOf("[") + 1), temp.indexOf("\"]}"));
		temp2 = temp2.replaceAll("\"", "");
		temp2 = temp2.replaceAll(" ", "");
		List<String> ids = Arrays.asList(temp2.split(","));
		
		//If the user does have cards (the specified user document as a list of card ids in it)
		if (ids != null) {
				
			// Build id string
			String cardIds = ids.get(0);

			for (int i=1;i<ids.size();i++) {
				cardIds = cardIds.concat("-" + ids.get(i));
			}
			// Get all the orders from the REST API
			String url = "http://" + hostname + ":" + port + "/service/getcard/" + cardIds;
			RestTemplate restTemplate = new RestTemplate();
			List<Card> cards = new ArrayList();
			try {
				ResponseEntity<List<Card>> rateResponse = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Card>>() {
					});
			cards = rateResponse.getBody();
			}
			catch(RestClientException e) {
				 ResponseEntity.status(HttpStatus.CONFLICT).body("Could not connect");
			}
			

			// Display the cards
			model.addAttribute("title", "List of Cards");	
			model.addAttribute("cards", cards);
		}
		
		//If the user doesn't have any cards
		else {
			model.addAttribute("title", "List of Cards | You currently don't have any cards on your account, click add to add one");
			model.addAttribute("cards", null);
		}		

		return "myCards";
	}

	// @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@PostMapping(value = "/myCards/delete{id}")
	public String deleteCard(@RequestParam String id) {
		// model.addAttribute("id", id);
		CardEntity card = cardService.findById(id);
		cardService.delete(card);
		return "redirect:/index";
	} 
 
	// new
	// CardEntity(Card.getId(),Card.getName(),Card.getType(),Card.getRareStatus(),Card.getImage(),Card.getCondition());
	/*
	 * Results from hitting the submit button in the create card form
	 */
	@PostMapping(value = "/saveCard")
	public String saveCard(@Valid Card Card, BindingResult bindingResult, Model model,
			@ModelAttribute(value = "name") String name, @ModelAttribute(value = "type") String type,
			@ModelAttribute(value = "image") String image, @ModelAttribute(value = "rareStatus") String rareStatus,
			@ModelAttribute(value = "condition") String condition) {
		// Check for validation errors
		if (bindingResult.hasErrors()) {
			// Adds the model for the create card modal to use when validating data
			model.addAttribute("Card", new Card());
			return "myCards";
		}
		// Display the myCard view with new title to indicate successful results from
		// the create card form
		model.addAttribute("title", "New Card Created Successfully");
		// Adds the model for the create card modal to use when validating data
		model.addAttribute("Card", new Card());
		// Add the login model for the login form in the navbar
		model.addAttribute("loginModel", new LoginModel());
		// Adds card to database
		cardService.createCard(name, type, image, rareStatus, condition);
		return "myCards";
	}

	/*
	 * Results from hitting the add button on the mycards page
	 */
	@GetMapping(value = "/newCard")
	public String showNewCardForm(Model model) {
		// Adds card model to the page
		Card Card = new Card();
		model.addAttribute("Card", Card);
		return "newCard";
	}
}
