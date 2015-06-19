package com.bttraining.web.dto;

public class CustomerRegisterDTO {
	private boolean isSuccess;
	private String clientToken;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getClientToken() {
		return clientToken;
	}

	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}
}
