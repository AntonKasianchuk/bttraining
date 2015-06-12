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
import com.bttraining.service.PaymentService;
import com.bttraining.service.impl.PaymentServiceImpl;
import com.bttraining.util.converter.CustomerConverter;
import com.bttraining.web.dto.CustomerDTO;

@WebServlet(description = "Payment controller", urlPatterns = { "/payment" })
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentService paymentService = new PaymentServiceImpl();
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
			viewPath = "view/payment.jsp";
		} else {
			request.setAttribute("result", "Error. Check input parameters.");
			viewPath = "view/result.jsp";
		}
		rd = request.getRequestDispatcher(viewPath);
		rd.forward(request, response);
	}
}
