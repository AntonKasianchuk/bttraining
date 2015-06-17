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
import com.bttraining.service.TransactionService;
import com.bttraining.service.impl.TransactionServiceImpl;

@WebServlet("/cancelPayment")
public class CancelPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionService paymentService = new TransactionServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String transactionId = request.getParameter("transactionId");
		Result<Transaction> result = paymentService.voidTransaction(transactionId);
		String transactionStatus = result.getTarget().getStatus().toString();
		String resultValue;
		if (result.isSuccess()) {
			resultValue = "Canceled.";
		} else {
			resultValue = "Cannot be canceled. Error.";
		}
		request.setAttribute("result", resultValue);
		request.setAttribute("transactionStatus", transactionStatus);
		RequestDispatcher rd = request.getRequestDispatcher("view/result.jsp");
		rd.forward(request, response);
	}
}
