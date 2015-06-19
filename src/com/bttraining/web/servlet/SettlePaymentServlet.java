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

@WebServlet("/settlePayment")
public class SettlePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionFacade transactionFacade = new TransactionFacadeImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String transactionId = request.getParameter("transactionId");
		TransactionDTO transactionDTO = transactionFacade
				.submitTransactionForSettlement(transactionId);
		String viewPath;
		if (transactionDTO.isSuccess()) {
			request.setAttribute("transactionId", transactionId);
			viewPath = "view/cancel_payment.jsp";
		} else {
			request.setAttribute("result", "error");
			viewPath = "view/results.jsp";
		}
		request.setAttribute("transactionStatus",
				transactionDTO.getTransactionStatus());
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		rd.forward(request, response);
	}
}
