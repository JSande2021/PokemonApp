package com.project.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Card {

	public Card() {
		this.name = "";
		this.type = "";
		this.image = "";
		this.rareStatus = "";
		this.condition = "";

	}
	
	public Card(
		@NotNull(message = "Name is a required field") @Size(min = 1, max = 32, message = "Name must be between 1 and 32 characters") String name,
		@NotNull(message = "Type is a required field") @Size(min = 1, max = 32, message = "Type must be between 1 and 32 characters") String type,
		@NotNull(message = "Image is a required field") @Size(min = 1, max = 32, message = "Image must be between 1 and 32 characters") String image,
		@NotNull(message = "Rare Status is a required field") @Size(min = 1, max = 32, message = "Rare Status must be between 1 and 32 characters") String rareStatus,
		@NotNull(message = "Condition is a required field") @Size(min = 1, max = 32, message = "Condition must be between 1 and 32 characters") String condition) {
		this.name = name;
		this.type = type;
		this.image = image;
		this.rareStatus = rareStatus;
		this.condition = condition;

	}
	public Card(String id, String name, String type, String image, String rareStatus, String condition) {
		this.id=id;
		this.name = name;
		this.type = type;
		this.image = image;
		this.rareStatus = rareStatus;
		this.condition = condition;
	}
	@NotNull(message="Name is a required field")
	@Size(min=1, max=32, message="Name must be between 1 and 32 characters")
	private String name;
	@NotNull(message="Type is a required field")
	@Size(min=1, max=32, message="Type must be between 1 and 32 characters")
	private String type;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotNull(message="Image is a required field")
	@Size(min=1, max=32, message="Image must be between 1 and 32 characters")
	private String image;
	
	@NotNull(message="Rare Status is a required field")
	@Size(min=1, max=32, message="Rare Status must be between 1 and 32 characters")
	private String rareStatus;
	
	@NotNull(message="Condition is a required field")
	@Size(min=1, max=32, message="Condition must be between 1 and 32 characters")
	private String condition;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRareStatus() {
		return rareStatus;
	}
	public void setRareStatus(String rareStatus) {
		this.rareStatus = rareStatus;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
