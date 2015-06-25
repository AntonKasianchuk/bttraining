package com.bttraining.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bttraining.facade.ReportFacade;
import com.bttraining.facade.impl.ReportFacadeImpl;
import com.bttraining.web.dto.ReportDTO;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/report")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportFacade reportFacade = new ReportFacadeImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ReportDTO> reportDTOList = reportFacade.getYesterdayReport();
		RequestDispatcher rd = request
				.getRequestDispatcher("view/report.jsp");
		request.setAttribute("reports", reportDTOList);
		rd.forward(request, response);
	}

}
