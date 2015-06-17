package com.bttraining.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Transaction;
import com.bttraining.service.PaymentService;
import com.bttraining.service.impl.PaymentServiceImpl;
import com.bttraining.web.dto.TransactionDTO;

/**
 * Servlet implementation class TransactionList
 */
@WebServlet("/transactionsList")
public class TransactionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentService paymentService = new PaymentServiceImpl();
       
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String customerId = request.getParameter("customerId");

		Set<TransactionDTO> transactionDTOs = paymentService
				.getTransactionDTOsByCustomerId(customerId);
		RequestDispatcher rd = request
				.getRequestDispatcher("view/transaction_list.jsp");

		request.setAttribute("transactions", transactionDTOs);
		// request.setAttribute("transactionStatus", transactionStatus);
		rd.forward(request, response);
	}

}
