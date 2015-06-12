package com.bttraining.util.converter;

import javax.servlet.http.HttpServletRequest;

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
}
