package com.bttraining.web.dto;

public class CustomerRegisterDTO {
	private boolean registered;
	
	public boolean isRegistered() {
		return registered;
	}
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	private String clientToken;

	public String getClientToken() {
		return clientToken;
	}
	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}
}
