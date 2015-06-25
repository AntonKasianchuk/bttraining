package com.bttraining.service.impl;

import java.util.List;
import java.util.Map;

import com.bttraining.dao.ReportDAO;
import com.bttraining.dao.impl.ReportDAOImpl;
import com.bttraining.service.ReportService;

public class ReportServiceImpl implements ReportService {
	private ReportDAO reportDAO = new ReportDAOImpl();

	@Override
	public List<Map<String, String>> getYesterdayReportService() {
		List<Map<String, String>> reportList = reportDAO
				.getYesterdayReportList();
		return reportList;
	}

}
