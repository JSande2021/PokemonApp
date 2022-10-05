package com.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import com.project.service.CardsBusinessService;

@Configuration
public class SpringConfig {
	
	/*
	 * Spring bean for the CardsBusinessService
	 */
	@RequestScope
	@Bean(name="cardsBusinessService")
	public CardsBusinessService getCardsBusiness() 
	{
		return new CardsBusinessService();
	}
	
	/*
	 * Spring bean for the UsersBusinessService
	 */
//	@RequestScope
//	@Bean(name="usersBusinessService")
//	public UsersBusinessService getUsersBusiness()
//	{
//		return new UsersBusinessService();
//	}
	
}
