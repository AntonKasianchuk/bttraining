package com.bttraining.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.SettlementBatchSummary;
import com.braintreegateway.SettlementBatchSummaryGateway;
import com.bttraining.dao.impl.ReportDAOImpl;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ReportDAOImpl.class)
public class ReportDAOTest {

	private static final Calendar CALENDAR = Calendar.getInstance();
	private static final Boolean SUCCESS = Boolean.TRUE;

	@Mock
	private BraintreeGateway gateway;
	@InjectMocks
	private ReportDAO reportDAO = new ReportDAOImpl();
	private Result<SettlementBatchSummary> result;
	private List<Map<String,String>> expectedReportList;
	private SettlementBatchSummaryGateway settlementBatchSummaryGateway;
	private SettlementBatchSummary settlementBatchSummary;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		result = mock(Result.class);
		expectedReportList= mock(List.class);
		settlementBatchSummaryGateway = mock(SettlementBatchSummaryGateway.class);
		settlementBatchSummary = mock(SettlementBatchSummary.class);
	}
	@Test
	public void shouldRetrieveReportListIfSuccess(){
		// given
		when(gateway.settlementBatchSummary()).thenReturn(settlementBatchSummaryGateway);
		
		when(settlementBatchSummaryGateway.generate(any(Calendar.class))).thenReturn(result);
		when(result.isSuccess()).thenReturn(SUCCESS);
		when(result.getTarget()).thenReturn(settlementBatchSummary);
		when(settlementBatchSummary.getRecords()).thenReturn(expectedReportList);

		// when
		List<Map<String,String>> actualReportList = reportDAO.getYesterdayReportList();

		// then
		assertEquals("It should be same report list", expectedReportList, actualReportList);
	}
}
