package com.bttraining.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bttraining.service.CustomerService;
import com.bttraining.service.impl.CustomerServiceImp;
import com.bttraining.web.dto.CustomerDTO;

@WebServlet("/customerDetails")
public class CustomerDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerId  = request.getParameter("customerId");
		CustomerDTO customerDTO = customerService.getCustomerDTOById(customerId);
		RequestDispatcher rd = request.getRequestDispatcher("view/customer_edit.jsp");
		request.setAttribute("customer", customerDTO);
		rd.forward(request, response);
	}
}
