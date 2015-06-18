package com.bttraining.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bttraining.facade.CustomerFacade;
import com.bttraining.facade.impl.CustomerFacadeImpl;

/**
 * Servlet implementation class CustomerListServlet
 */
@WebServlet("/customerList")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerFacade customerFacade = new CustomerFacadeImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> customerIds = customerFacade.getCustomerIds();
 		RequestDispatcher rd = request.getRequestDispatcher("view/customer_list.jsp");
		
		request.setAttribute("customerIds", customerIds);
		rd.forward(request, response);
	}

}
