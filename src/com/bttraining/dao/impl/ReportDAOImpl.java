package com.bttraining.dao.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.SettlementBatchSummary;
import com.bttraining.configuration.Configurator;
import com.bttraining.dao.ReportDAO;

public class ReportDAOImpl implements ReportDAO {

	private BraintreeGateway gateway = Configurator.getBraintreeGateway();

	public void setGateway(BraintreeGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public List<Map<String, String>> getYesterdayReportList() {
		Result<SettlementBatchSummary> result = gateway
				.settlementBatchSummary().generate(Calendar.getInstance());

		if (result.isSuccess()) {
			List<Map<String, String>> records = result.getTarget().getRecords();
			return records;
		}
		return null;
	}

}
