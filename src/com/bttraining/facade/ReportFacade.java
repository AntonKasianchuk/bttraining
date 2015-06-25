package com.bttraining.facade;

import java.util.List;

import com.bttraining.web.dto.ReportDTO;

public interface ReportFacade {
	List<ReportDTO> getYesterdayReport();
}
