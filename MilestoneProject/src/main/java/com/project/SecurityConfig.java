package com.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.service.UserLoginBusinessService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	UserLoginBusinessService service;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Bean(name = "passwordEncoder")
	public static BCryptPasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * Configures the application security
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.csrf().disable()
			.authorizeRequests()
				//These pages are accessible without logging in
				.antMatchers("/", "/images/**", "/service/**", "/about", "/index", "/loginFailure", "/newUser", "/saveUser").permitAll()
				.anyRequest().authenticated()
				.and()
			//Default login page
			.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()				
				.defaultSuccessUrl("/myCards", true)
				.and()
			//Logout path
			.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.permitAll()
				.logoutSuccessUrl("/");
	}
	
	/*
	 * Sets the password and username
	 */
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth
		.userDetailsService(service)
		.passwordEncoder(passwordEncoder);
	}
}

	