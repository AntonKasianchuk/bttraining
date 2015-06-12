package com.bttraining.web.servlet;

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
import com.bttraining.Configuration;
import com.bttraining.service.PaymentService;
import com.bttraining.service.impl.PaymentServiceImpl;

@WebServlet(description = "Payment controller", urlPatterns = { "/payment" })
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentService paymentService = new PaymentServiceImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CustomerRequest customerRequest = paymentService
				.generateCustomerRequest(request);
		Result<Customer> result = paymentService
				.getCustomerResultByCustomerRequest(customerRequest);
		RequestDispatcher rd;
		if (result.isSuccess()) {
			String customerId = result.getTarget().getId();

			String clientToken = paymentService
					.getClientTokenByCustomerId(customerId);
			request.setAttribute("token", clientToken);
			rd = request.getRequestDispatcher("view/payment.jsp");
		} else {
			request.setAttribute("result", "Error. Check input parameters.");
			rd = request.getRequestDispatcher("view/result.jsp");
		}
		rd.forward(request, response);
	}
}
