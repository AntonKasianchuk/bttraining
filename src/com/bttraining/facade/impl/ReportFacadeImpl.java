package com.bttraining.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bttraining.facade.ReportFacade;
import com.bttraining.facade.converter.ReportConverter;
import com.bttraining.service.ReportService;
import com.bttraining.service.impl.ReportServiceImpl;
import com.bttraining.web.dto.ReportDTO;

public class ReportFacadeImpl implements ReportFacade {

	private ReportService reportService = new ReportServiceImpl();
	private ReportConverter reportConverter = new ReportConverter();
	@Override
	public List<ReportDTO> getYesterdayReport() {
		List<ReportDTO> reportDTOList = new ArrayList<>();
		List<Map<String,String>> reportList =  reportService.getYesterdayReportService();
		for (Map<String, String> reportMap : reportList) {
			ReportDTO reportDTO = reportConverter.generateReportDTO(reportMap);
			reportDTOList.add(reportDTO);
		}
		
		return reportDTOList;
	}

}
