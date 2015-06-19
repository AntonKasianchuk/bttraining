package com.bttraining.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bttraining.facade.CustomerFacade;
import com.bttraining.facade.impl.CustomerFacadeImpl;

/**
 * Servlet implementation class CreateTransactionServlet
 */
@WebServlet("/createTransaction")
public class CreateTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerFacade customerFacade = new CustomerFacadeImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String customerId = request.getParameter("customerId");
		String clientToken = customerFacade
				.getClientTokenByCustomerId(customerId);
		request.setAttribute("token", clientToken);
		RequestDispatcher rd = request
				.getRequestDispatcher("view/choose_payment_form.jsp");
		rd.forward(request, response);
	}

}
