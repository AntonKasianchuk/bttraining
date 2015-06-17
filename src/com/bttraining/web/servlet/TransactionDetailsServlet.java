package com.bttraining.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bttraining.web.dto.CustomerDTO;

/**
 * Servlet implementation class EditTransactionServlet
 */
@WebServlet("/transactionDetails")
public class TransactionDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String transactionId  = request.getParameter("transactionId");
		RequestDispatcher rd = request.getRequestDispatcher("view/transaction_edit.jsp");
		request.setAttribute("transactionId", transactionId);
		rd.forward(request, response);
	}

}
