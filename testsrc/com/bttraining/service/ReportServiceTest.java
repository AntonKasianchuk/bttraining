package com.bttraining.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.bttraining.dao.ReportDAO;
import com.bttraining.service.impl.ReportServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ReportServiceImpl.class)
public class ReportServiceTest {

	@Mock
	private ReportDAO reportDAO;
	@InjectMocks
	private ReportService reportService = new ReportServiceImpl();
	private List<Map<String, String>> expectedReportList;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp(){
		expectedReportList = mock(List.class);
	}

	@Test
	public void shouldRetrieveReportDTO(){
		// given
		when(reportDAO.getYesterdayReportList()).thenReturn(expectedReportList);
		// when
		List<Map<String, String>> actualReportList = reportService.getYesterdayReportService();
		// given
		assertEquals("It should be the same report list", expectedReportList, actualReportList);
	}
}
