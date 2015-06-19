package com.bttraining.facade.converter;

import java.util.Map;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.bttraining.web.dto.CustomerInfoDTO;

public class CustomerConverter {

	public CustomerInfoDTO generateCustomerDTO(
			Map<String, String[]> customerParameterMap) {
		CustomerInfoDTO customerDTO = new CustomerInfoDTO();
		customerDTO.setFirstName(customerParameterMap.get("first_name")[0]);
		customerDTO.setLastName(customerParameterMap.get("last_name")[0]);
		customerDTO.setCompany(customerParameterMap.get("company")[0]);
		customerDTO.setEmail(customerParameterMap.get("email")[0]);
		customerDTO.setPhone(customerParameterMap.get("phone")[0]);
		customerDTO.setFax(customerParameterMap.get("fax")[0]);
		customerDTO.setWebsite(customerParameterMap.get("website")[0]);
		return customerDTO;
	}

	public CustomerInfoDTO generateCustomerDTO(Customer customer) {
		CustomerInfoDTO customerDTO = new CustomerInfoDTO();
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setCompany(customer.getCompany());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setPhone(customer.getPhone());
		customerDTO.setFax(customer.getFax());
		customerDTO.setWebsite(customer.getWebsite());
		return customerDTO;
	}

	public CustomerRequest generateCustomerRequest(CustomerInfoDTO customerDTO) {
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.firstName(customerDTO.getFirstName());
		customerRequest.lastName(customerDTO.getLastName());
		customerRequest.company(customerDTO.getCompany());
		customerRequest.email(customerDTO.getEmail());
		customerRequest.phone(customerDTO.getPhone());
		customerRequest.fax(customerDTO.getFax());
		customerRequest.website(customerDTO.getWebsite());
		return customerRequest;
	}
}
