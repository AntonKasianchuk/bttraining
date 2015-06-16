package com.bttraining.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.braintreegateway.Customer;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.bttraining.service.CustomerService;
import com.bttraining.service.RegisterService;
import com.bttraining.service.impl.CustomerServiceImp;
import com.bttraining.service.impl.RegisterServiceImpl;

/**
 * Servlet implementation class CustomerListServlet
 */
@WebServlet("/customer_list")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerServiceImp();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceCollection<Customer> collection = customerService.getAllCustomers();
		Set<String> customerIds = customerService.getCustomerIds();
 		RequestDispatcher rd = request.getRequestDispatcher("view/customer_list.jsp");
		
		request.setAttribute("customerIds", customerIds);
		//request.setAttribute("transactionStatus", transactionStatus);
		rd.forward(request, response);
	}

}
