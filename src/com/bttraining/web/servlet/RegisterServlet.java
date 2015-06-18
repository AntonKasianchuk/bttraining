package com.bttraining.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.braintreegateway.Customer;
import com.bttraining.facade.CustomerFacade;
import com.bttraining.facade.impl.CustomerFacadeImpl;
import com.bttraining.web.dto.CustomerRegisterDTO;

@WebServlet(description = "Payment controller", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerFacade customerFacade = new CustomerFacadeImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> customerParameterMap = request.getParameterMap();
		CustomerRegisterDTO customerRegisterDTO = customerFacade
				.createCustomer(customerParameterMap);

		RequestDispatcher rd;
		String viewPath;
		if (customerRegisterDTO.isRegistered()) {
			String clientToken = customerRegisterDTO.getClientToken();
			request.setAttribute("token", clientToken);
			viewPath = "view/choose_payment_form.jsp";
		} else {
			request.setAttribute("result", "Error. Check input parameters.");
			viewPath = "view/result.jsp";
		}
		rd = request.getRequestDispatcher(viewPath);
		rd.forward(request, response);
	}
}
