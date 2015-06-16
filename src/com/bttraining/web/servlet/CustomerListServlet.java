package com.bttraining.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bttraining.service.CustomerService;
import com.bttraining.service.impl.CustomerServiceImp;

/**
 * Servlet implementation class CustomerListServlet
 */
@WebServlet("/customerList")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerServiceImp();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> customerIds = customerService.getCustomerIds();
 		RequestDispatcher rd = request.getRequestDispatcher("view/customer_list.jsp");
		
		request.setAttribute("customerIds", customerIds);
		//request.setAttribute("transactionStatus", transactionStatus);
		rd.forward(request, response);
	}

}
