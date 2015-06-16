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
import com.bttraining.service.PaymentService;
import com.bttraining.service.impl.PaymentServiceImpl;

@WebServlet("/settlePayment")
public class SettlePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentService paymentService = new PaymentServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String transactionId = request.getParameter("transactionId");
		Result<Transaction> result = paymentService.submitTransactionForSettlement(transactionId);
		String transactionStatus = result.getTarget().getStatus().toString();
		String viewPath;
		if (result.isSuccess()) {
			request.setAttribute("transactionId", transactionId);
			viewPath = "view/cancel_payment.jsp";
		} else {
			request.setAttribute("result", "error");
			viewPath = "view/results.jsp";
		}
		request.setAttribute("transactionStatus", transactionStatus);
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		rd.forward(request, response);
	}

}
