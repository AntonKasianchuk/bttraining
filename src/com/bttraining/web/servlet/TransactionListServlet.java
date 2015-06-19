package com.bttraining.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bttraining.facade.TransactionFacade;
import com.bttraining.facade.impl.TransactionFacadeImpl;
import com.bttraining.web.dto.TransactionInfoDTO;

/**
 * Servlet implementation class TransactionList
 */
@WebServlet("/transactionsList")
public class TransactionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionFacade transactionFacade = new TransactionFacadeImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String customerId = request.getParameter("customerId");

		Set<TransactionInfoDTO> transactionDTOs = transactionFacade
				.getTransactionInfoDTOsByCustomerId(customerId);
		RequestDispatcher rd = request
				.getRequestDispatcher("view/transaction_list.jsp");

		request.setAttribute("transactions", transactionDTOs);
		request.setAttribute("customerId", customerId);
		rd.forward(request, response);
	}
}
