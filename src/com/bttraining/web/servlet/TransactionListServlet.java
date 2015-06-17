package com.bttraining.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bttraining.service.TransactionService;
import com.bttraining.service.impl.TransactionServiceImpl;
import com.bttraining.web.dto.TransactionDTO;

/**
 * Servlet implementation class TransactionList
 */
@WebServlet("/transactionsList")
public class TransactionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionService paymentService = new TransactionServiceImpl();
       
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String customerId = request.getParameter("customerId");

		Set<TransactionDTO> transactionDTOs = paymentService
				.getTransactionDTOsByCustomerId(customerId);
		RequestDispatcher rd = request
				.getRequestDispatcher("view/transaction_list.jsp");

		request.setAttribute("transactions", transactionDTOs);
		request.setAttribute("customerId",  customerId);
		rd.forward(request, response);
	}

}
