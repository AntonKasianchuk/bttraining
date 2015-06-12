package com.bttraining.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.bttraining.service.CheckoutService;
import com.bttraining.service.impl.CheckoutServiceImpl;

@WebServlet(description = "Checkout controller", urlPatterns = { "/checkout" })
public class CheckoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CheckoutService checkoutService = new CheckoutServiceImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String methodNonce = (String) request
				.getParameter("payment_method_nonce");
		String amountString = (String) request.getParameter("amount");
		Result<Transaction> result = checkoutService
				.doTransactionByMethodNonceAndAmount(methodNonce, amountString);

		String resultValue = "Failure";
		if (result.isSuccess()) {
			resultValue = "Success transaction";
		}
		request.setAttribute("result", resultValue);
		RequestDispatcher rd = request.getRequestDispatcher("view/result.jsp");
		rd.forward(request, response);
	}
}
