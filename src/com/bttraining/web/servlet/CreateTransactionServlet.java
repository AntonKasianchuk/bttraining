package com.bttraining.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.braintreegateway.Customer;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.bttraining.service.CustomerService;
import com.bttraining.service.RegisterService;
import com.bttraining.service.impl.CustomerServiceImp;
import com.bttraining.service.impl.RegisterServiceImpl;

/**
 * Servlet implementation class CreateTransactionServlet
 */
@WebServlet("/createTransaction")
public class CreateTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterService registerService = new RegisterServiceImpl();
	private CustomerService customerService = new CustomerServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerId  = request.getParameter("customerId");
		ResourceCollection<Customer> customerCollection = customerService.getCustomerById(customerId);
		Customer customer = customerCollection.getFirst();
		String clientToken = registerService.getClientTokenByCustomer(customer);
		request.setAttribute("token", clientToken);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/choose_payment_form.jsp");
		rd.forward(request, response);
	}

}
