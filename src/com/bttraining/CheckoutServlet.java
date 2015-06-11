package com.bttraining;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

@WebServlet(description = "Checkout controller", urlPatterns = { "/checkout" })
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BraintreeGateway gateway = Configuration.getBraintreeGateway();
		String methodNonce = (String) request.getParameter("payment_method_nonce");
		TransactionRequest txRequest = new TransactionRequest().amount(
				new BigDecimal("100.00")).paymentMethodNonce(methodNonce);

		Result<Transaction> result = gateway.transaction().sale(txRequest);
		
		request.setAttribute("result", result.toString());

		RequestDispatcher rd = request.getRequestDispatcher("view/result.jsp");
		rd.forward(request, response);
	}

}
