package com.bttraining.util.converter;

import javax.servlet.http.HttpServletRequest;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.bttraining.web.dto.CustomerDTO;

public class CustomerConverter {

	public CustomerDTO generateCustomerDTO(HttpServletRequest request) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(request.getParameter("first_name"));
		customerDTO.setLastName(request.getParameter("last_name"));
		customerDTO.setCompany(request.getParameter("company"));
		customerDTO.setEmail(request.getParameter("email"));
		customerDTO.setPhone(request.getParameter("phone"));
		customerDTO.setFax(request.getParameter("fax"));
		customerDTO.setWebsite(request.getParameter("website"));
		return customerDTO;
	}
	
	public CustomerDTO generateCustomerDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setCompany(customer.getCompany());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setPhone(customer.getPhone());
		customerDTO.setFax(customer.getFax());
		customerDTO.setWebsite(customer.getWebsite());
		return customerDTO;
	}
	
	public CustomerRequest generateCustomerRequest(CustomerDTO customerDTO) {
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
