package com.bttraining.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateTransactionServlet
 */
@WebServlet("/createTransaction")
public class CreateTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerId  = request.getParameter("customerId");
		request.setAttribute("customerId", customerId);
		RequestDispatcher rd = request.getRequestDispatcher("view/create_transaction.jsp");
		rd.forward(request, response);
	}

}
