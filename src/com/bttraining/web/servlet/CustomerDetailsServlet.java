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
import com.bttraining.web.dto.CustomerInfoDTO;

@WebServlet("/customerDetails")
public class CustomerDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerFacade customerFacade = new CustomerFacadeImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String customerId = request.getParameter("customerId");
		CustomerInfoDTO customerDTO = customerFacade
				.getCustomerDTOById(customerId);
		RequestDispatcher rd = request
				.getRequestDispatcher("view/customer_edit.jsp");
		request.setAttribute("customer", customerDTO);
		request.setAttribute("customerId", customerId);
		rd.forward(request, response);
	}
}
