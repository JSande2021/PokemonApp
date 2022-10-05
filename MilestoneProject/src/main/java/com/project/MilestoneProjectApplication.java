package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.project"})
@SpringBootApplication
public class MilestoneProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MilestoneProjectApplication.class, args);
	}

}
