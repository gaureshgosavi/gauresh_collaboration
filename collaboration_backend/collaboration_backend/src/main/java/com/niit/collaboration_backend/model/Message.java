package com.niit.collaboration_backend.model;

public class Message {

	private String message;
	private String username; 
	private int id;

	public Message() {

	}

	public Message(int id, String message, String username) {
		this.id = id;
		this.message = message;
		this.username = username;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
