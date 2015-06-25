package com.bttraining.web.dto;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.bttraining.facade.ReportFacade;
import com.bttraining.facade.converter.ReportConverter;
import com.bttraining.facade.impl.ReportFacadeImpl;
import com.bttraining.service.ReportService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ReportFacadeImpl.class)
public class ReportFacadeTest {

	@InjectMocks
	private ReportFacade reportFacade = new ReportFacadeImpl();
	@Mock
	private ReportService reportService;
	private ReportDTO reportDTO;
	private List<Map<String, String>> reportList;
	private ReportConverter reportConverter;
	private ArrayList<ReportDTO> expectedReportDTOList;
	private Map<String, String> reportMap;
	private Iterator<Map<String, String>> iterator;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		expectedReportDTOList = (ArrayList<ReportDTO>) mock(ArrayList.class);
		reportList = mock(List.class);
		reportConverter = mock(ReportConverter.class);
		reportMap = mock(Map.class);
		iterator = mock(Iterator.class);
		reportDTO = mock(ReportDTO.class);
	}

	@Test
	public void shouldRetrieveYesterdayReport() throws Exception{
		// given
		whenNew(ArrayList.class).withNoArguments().thenReturn(
				expectedReportDTOList);
		when(reportService.getYesterdayReportService()).thenReturn(reportList);
		when(iterator.hasNext()).thenReturn(true, false);
		when(iterator.next()).thenReturn(reportMap);
		when(reportList.iterator()).thenReturn(iterator);
		when(reportConverter.generateReportDTO(reportMap)).thenReturn(reportDTO);

		// when
		List<ReportDTO> actualReportDTOList = reportFacade.getYesterdayReport();

		// then
		assertEquals("Must be the same report DTO list but it is different", expectedReportDTOList, actualReportDTOList);
	}
}
