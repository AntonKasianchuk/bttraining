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

@WebServlet(description = "Checkout controller", urlPatterns = { "/payment" })
public class PaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TransactionFacade transactionFacade = new TransactionFacadeImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String methodNonce = (String) request
				.getParameter("payment_method_nonce");
		String amountString = (String) request.getParameter("amount");
		TransactionDTO transactionCreateDTO = transactionFacade
				.doTransactionByMethodNonceAndAmount(methodNonce, amountString);
		String resultValue;
		RequestDispatcher rd;
		if (transactionCreateDTO.isSuccess()) {
			resultValue = "Success transaction";
			request.setAttribute("transactionId",
					transactionCreateDTO.getTransactionId());
			request.setAttribute("transactionStatus",
					transactionCreateDTO.getTransactionStatus());
			rd = request.getRequestDispatcher("view/confirm_payment.jsp");
		} else {
			resultValue = "Failure";
			rd = request.getRequestDispatcher("view/result.jsp");
		}
		request.setAttribute("result", resultValue);
		rd.forward(request, response);
	}
}
