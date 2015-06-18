package com.bttraining.web.dto;

public class CustomerEditDTO {
	private boolean edited;
	private CustomerDTO customerDTO;
	
	public boolean isEdited() {
		return edited;
	}
	public void setEdited(boolean edited) {
		this.edited = edited;
	}
	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}
	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}
}
