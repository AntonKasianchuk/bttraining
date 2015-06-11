package com.bttraining;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;
import com.braintreegateway.ValidationErrors;

@WebServlet(description = "Payment controller", urlPatterns = { "/payment" })
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BraintreeGateway gateway = Configuration.getBraintreeGateway();
		CustomerRequest customerRequest = createCustomerRequest(request);	
		Result<Customer> result = gateway.customer().create(customerRequest);

		RequestDispatcher rd;
		if (result.isSuccess()) {			
			String customerId = result.getTarget().getId();
			
			// String clientToken = gateway.clientToken().generate();
			ClientTokenRequest clientTokenRequest = new ClientTokenRequest()
			.customerId(customerId);
			String clientToken = gateway.clientToken().generate(clientTokenRequest);
			
			request.setAttribute("token", clientToken);
			rd = request.getRequestDispatcher("view/payment.jsp");
		} else {
			request.setAttribute("result", "Error. Check input parameters.");
			rd = request.getRequestDispatcher("view/result.jsp");
		}
		rd.forward(request, response);
	}

	private CustomerRequest createCustomerRequest(HttpServletRequest request) {
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.firstName(request.getParameter("first_name"));
		customerRequest.lastName(request.getParameter("last_name"));
		customerRequest.company(request.getParameter("company"));
		customerRequest.email(request.getParameter("email"));
		customerRequest.phone(request.getParameter("phone"));
		customerRequest.fax(request.getParameter("fax"));
		customerRequest.website(request.getParameter("website"));
		return customerRequest;
	}
}
