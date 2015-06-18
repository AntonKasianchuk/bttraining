package com.bttraining.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.braintreegateway.CustomerRequest;
import com.bttraining.facade.CustomerFacade;
import com.bttraining.facade.converter.CustomerConverter;
import com.bttraining.facade.impl.CustomerFacadeImpl;
import com.bttraining.web.dto.CustomerDTO;
import com.bttraining.web.dto.CustomerEditDTO;

/**
 * Servlet implementation class EditCustomerServlet
 */
@WebServlet("/editCustomer")
public class EditCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerFacade customerFacade = new CustomerFacadeImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerId  = request.getParameter("customerId");
		Map<String, String[]> customerParameterMap = request.getParameterMap();
				
		CustomerEditDTO customerEditDTO = customerFacade.updateCustomer(customerId, customerParameterMap);
		
		if (customerEditDTO.isEdited()) {
			request.setAttribute("result", "Saved");
		} else {
			request.setAttribute("result", "Error. Cannot be saved");
		}
		request.setAttribute("customer", customerEditDTO.getCustomerDTO());
		request.setAttribute("customerId", customerId);
		RequestDispatcher rd = request.getRequestDispatcher("view/customer_edit.jsp");
		rd.forward(request, response);
	}
}
