package com.bttraining.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Result;
import com.bttraining.service.RegisterService;
import com.bttraining.service.impl.RegisterServiceImpl;
import com.bttraining.util.converter.CustomerConverter;
import com.bttraining.web.dto.CustomerDTO;

@WebServlet(description = "Payment controller", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterService paymentService = new RegisterServiceImpl();
	private CustomerConverter customerConverter = new CustomerConverter();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CustomerDTO customerDTO = customerConverter
				.generateCustomerDTO(request);
		CustomerRequest customerRequest = paymentService
				.generateCustomerRequest(customerDTO);
		Result<Customer> result = paymentService
				.getCustomerResultByCustomerRequest(customerRequest);
		RequestDispatcher rd;
		String viewPath;
		
		if (result.isSuccess()) {
			String clientToken = paymentService.getClientTokenByResult(result);
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
