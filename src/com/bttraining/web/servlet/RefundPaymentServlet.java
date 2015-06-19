package com.bttraining.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bttraining.facade.TransactionFacade;
import com.bttraining.facade.impl.TransactionFacadeImpl;
import com.bttraining.web.dto.TransactionDTO;

/**
 * Servlet implementation class EditTransactionServlet
 */
@WebServlet("/refundPayment")
public class RefundPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionFacade transactionFacade = new TransactionFacadeImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String transactionId = request.getParameter("transactionId");
		TransactionDTO result = transactionFacade
				.refundTransaction(transactionId);
		String resultValue;
		if (result.isSuccess()) {
			resultValue = "Refunded.";
		} else {
			resultValue = "Cannot be refunded. Error.";
		}
		request.setAttribute("result", resultValue);
		request.setAttribute("transactionStatus", result.getTransactionStatus());
		RequestDispatcher rd = request.getRequestDispatcher("view/result.jsp");
		rd.forward(request, response);
	}

}
