package com.bttraining.facade.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;

import com.bttraining.web.dto.ReportDTO;

public class ReportConverterTest {
	private static final String KIND = "test_kind";
	private static final String MERCHANT_ACCOUNT_ID = "test_merchant_account_id";
	private static final String CARD_TYPE = "test_card_type";
	private static final String AMOUNT_SETTLED = "test_amount_settled";
	private static final String COUNT = "test_count";

	private ReportConverter sut = new ReportConverter();

	@Test
	public void shouldConvertReportMapToReportDTO() {
		// given
		@SuppressWarnings("unchecked")
		Map<String, String> reportMap = mock(Map.class);
		when(reportMap.get("kind")).thenReturn(KIND);
		when(reportMap.get("merchant_account_id")).thenReturn(
				MERCHANT_ACCOUNT_ID);
		when(reportMap.get("card_type")).thenReturn(CARD_TYPE);
		when(reportMap.get("count")).thenReturn(AMOUNT_SETTLED);
		when(reportMap.get("amount_settled")).thenReturn(COUNT);

		// when
		ReportDTO reportDTO = sut.generateReportDTO(reportMap);

		// then
		assertEquals(reportDTO.getKind(), KIND);
		assertEquals(reportDTO.getMerchantAccountId(), MERCHANT_ACCOUNT_ID);
		assertEquals(reportDTO.getCardType(), CARD_TYPE);
		assertEquals(reportDTO.getCount(), AMOUNT_SETTLED);
		assertEquals(reportDTO.getAmountSettled(), COUNT);
	}

}
