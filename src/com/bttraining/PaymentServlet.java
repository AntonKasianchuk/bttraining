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

@WebServlet(description = "Payment controller", urlPatterns = { "/payment" })
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String customerId = "987";
		BraintreeGateway gateway = Configuration.getBraintreeGateway();

		// String clientToken = gateway.clientToken().generate();
		ClientTokenRequest clientTokenRequest = new ClientTokenRequest()
				.customerId(customerId);
		String clientToken = gateway.clientToken().generate(clientTokenRequest);

		request.setAttribute("token", clientToken);

		RequestDispatcher rd = request.getRequestDispatcher("view/payment.jsp");
		rd.forward(request, response);
	}

}
