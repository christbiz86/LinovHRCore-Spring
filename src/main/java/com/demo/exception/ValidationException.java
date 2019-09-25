package com.demo.exception;

import java.util.List;

@SuppressWarnings("serial")

public class ValidationException extends Exception {

	private String messages;

	public ValidationException(String messages) {
		this.messages = messages;
	}
	
	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}
}
