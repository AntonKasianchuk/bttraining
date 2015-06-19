package com.bttraining.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Choose form controller", urlPatterns = { "/paymentForm" })
public class PaymentDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		String viewPath;
		String paymentForm = request.getParameter("payment_form");
		if ("custom".equalsIgnoreCase(paymentForm)) {
			viewPath = "view/custom_payment.jsp";
		} else {
			viewPath = "view/dropin_payment.jsp";
		}
		request.setAttribute("token", request.getParameter("token"));
		rd = request.getRequestDispatcher(viewPath);
		rd.forward(request, response);
	}
}
