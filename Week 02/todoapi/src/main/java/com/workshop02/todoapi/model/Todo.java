package com.workshop02.todoapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private boolean isComplete;
	
	public Todo() {}
	
	public Todo(String name, boolean isComplete) {
		this.name = name;
		this.isComplete = isComplete;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getIsComplete() {
		return this.isComplete;
	}
	
	public void setIsComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
}
