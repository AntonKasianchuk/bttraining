package com.bttraining.facade.converter;

import java.util.Map;

import com.bttraining.web.dto.ReportDTO;

public class ReportConverter {

	public ReportDTO generateReportDTO(Map<String, String> reportMap) {
		ReportDTO reportDTO = new ReportDTO();
		reportDTO.setKind(reportMap.get("kind"));
		reportDTO.setMerchantAccountId(reportMap.get("merchant_account_id"));
		reportDTO.setCardType(reportMap.get("card_type"));
		reportDTO.setAmountSettled(reportMap.get("amount_settled"));
		reportDTO.setCount(reportMap.get("count"));
		return reportDTO;
	}

}
