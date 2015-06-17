package com.bttraining.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;
import com.bttraining.service.CustomerService;
import com.bttraining.service.impl.CustomerServiceImp;
import com.bttraining.util.converter.CustomerConverter;
import com.bttraining.web.dto.CustomerDTO;

/**
 * Servlet implementation class EditCustomerServlet
 */
@WebServlet("/editCustomer")
public class EditCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerConverter customerConverter = new CustomerConverter();   
	private CustomerService customerService = new CustomerServiceImp();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerId  = request.getParameter("customerId");
		CustomerDTO customerDTO = customerConverter
				.generateCustomerDTO(request);
		CustomerRequest customerRequest = customerConverter
				.generateCustomerRequest(customerDTO);
		Result<Customer> result = customerService.updateCustomer(customerId, customerRequest);
		
		if (result.isSuccess()) {
			request.setAttribute("result", "Saved");
		} else {
			request.setAttribute("result", "Error. Cannot be saved");
		}
		request.setAttribute("customer", customerDTO);
		request.setAttribute("customerId", customerId);
		RequestDispatcher rd = request.getRequestDispatcher("view/customer_edit.jsp");
		rd.forward(request, response);
		
		
	}

}
