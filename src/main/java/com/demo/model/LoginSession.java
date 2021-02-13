
package com.demo.model;

public class LoginSession {


	private String token;
	public String getToken() {
		return token;
	}
	private void setToken(String token) {
		this.token = token;
	}


	private User user;
	public User getUser() {
		return user;
	}
	private void setUser(User user) {
		this.user = user;
	}


	public LoginSession(String token, User user) {
		this.setToken(token);
		this.setUser(user);
	}


}

