package com.demo.exception;

import java.util.List;

@SuppressWarnings("serial")
public class ValidationException extends Exception {
	private List<String> messages;

	public ValidationException(List<String> messages){
		this.messages = messages;
	}

	public ValidationException(String message) {
		super(message);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}