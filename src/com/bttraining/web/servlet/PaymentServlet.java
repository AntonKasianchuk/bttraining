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
import com.braintreegateway.Transaction.Status;
import com.bttraining.service.PaymentService;
import com.bttraining.service.impl.PaymentServiceImpl;

@WebServlet(description = "Checkout controller", urlPatterns = { "/payment" })
public class PaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PaymentService checkoutService = new PaymentServiceImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String methodNonce = (String) request
				.getParameter("payment_method_nonce");
		String amountString = (String) request.getParameter("amount");
		Result<Transaction> result = checkoutService
				.doTransactionByMethodNonceAndAmount(methodNonce, amountString);
		
		String transactionId = result.getTransaction().getId();
		String transactionStatus = result.getTransaction().getStatus().toString();
		String resultValue = "Failure";
		RequestDispatcher rd;
		if (result.isSuccess()) {
			resultValue = "Success transaction";
			request.setAttribute("transactionId", transactionId);
			request.setAttribute("transactionStatus", transactionStatus);
			rd = request.getRequestDispatcher("view/confirmPayment.jsp");
		} else {
			rd = request.getRequestDispatcher("view/confirmPayment.jsp");
		}
		request.setAttribute("result", resultValue);
		rd.forward(request, response);
	}
}
