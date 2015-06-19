package com.bttraining.web.dto;

public class CustomerDTO {
	private boolean isSuccess;
	private CustomerInfoDTO customerInfoDTO;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public CustomerInfoDTO getCustomerInfoDTO() {
		return customerInfoDTO;
	}

	public void setCustomerInfoDTO(CustomerInfoDTO customerInfoDTO) {
		this.customerInfoDTO = customerInfoDTO;
	}
}
