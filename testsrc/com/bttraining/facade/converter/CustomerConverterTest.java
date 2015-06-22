package com.bttraining.facade.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.bttraining.web.dto.CustomerInfoDTO;

public class CustomerConverterTest {
	
	private static final String FIRST_NAME = "test first name";
	private static final String LAST_NAME = "test last name";
	private static final String COMPANY = "test_company";
	private static final String EMAIL = "test_email";
	private static final String PHONE = "test_phone";
	private static final String FAX = "test_fax";
	private static final String WEBSITE = "test_website";
	
	private final CustomerConverter unit = new CustomerConverter();

	@Test
	public void shouldConvertCustomerToCustomerDTO() {
		// given
		Customer customer = mock(Customer.class);
		when(customer.getFirstName()).thenReturn(FIRST_NAME);
		when(customer.getLastName()).thenReturn(LAST_NAME);
		when(customer.getCompany()).thenReturn(COMPANY);
		when(customer.getEmail()).thenReturn(EMAIL);
		when(customer.getPhone()).thenReturn(PHONE);
		when(customer.getFax()).thenReturn(FAX);
		when(customer.getWebsite()).thenReturn(WEBSITE);

		// when
		CustomerInfoDTO customerDTO = unit.generateCustomerDTO(customer);

		// then
		assertEquals(customerDTO.getFirstName(), FIRST_NAME);
		assertEquals(customerDTO.getLastName(), LAST_NAME);
		assertEquals(customerDTO.getCompany(), COMPANY);
		assertEquals(customerDTO.getEmail(), EMAIL);
		assertEquals(customerDTO.getPhone(), PHONE);
		assertEquals(customerDTO.getFax(), FAX);
		assertEquals(customerDTO.getWebsite(), WEBSITE);
	}
	
	@Test
	public void shouldCustomerDTOToConvertCustomerRequest() {
		// given
		CustomerInfoDTO customerInfoDTO = mock(CustomerInfoDTO.class);

		// when
		CustomerRequest customerRequest = unit.generateCustomerRequest(customerInfoDTO);

		// then
		verify(customerInfoDTO).getFirstName();
		verify(customerInfoDTO).getLastName();
		verify(customerInfoDTO).getCompany();
		verify(customerInfoDTO).getEmail();
		verify(customerInfoDTO).getPhone();
		verify(customerInfoDTO).getFax();
		verify(customerInfoDTO).getWebsite();
		assertNotNull(customerRequest);
	}

	@Test
	public void shouldConvertParametersMapToCustomerDTO() {
		// given
		@SuppressWarnings("unchecked")
		Map<String, String[]> customerParameterMap = mock(Map.class);
		when(customerParameterMap.get("first_name")).thenReturn(new String[]{FIRST_NAME});
		when(customerParameterMap.get("last_name")).thenReturn(new String[]{LAST_NAME});
		when(customerParameterMap.get("company")).thenReturn(new String[]{COMPANY});
		when(customerParameterMap.get("email")).thenReturn(new String[]{EMAIL});
		when(customerParameterMap.get("phone")).thenReturn(new String[]{PHONE});
		when(customerParameterMap.get("fax")).thenReturn(new String[]{FAX});
		when(customerParameterMap.get("website")).thenReturn(new String[]{WEBSITE});

		// when
		CustomerInfoDTO customerDTO = unit.generateCustomerDTO(customerParameterMap);

		// then
		assertEquals(customerDTO.getFirstName(), FIRST_NAME);
		assertEquals(customerDTO.getLastName(), LAST_NAME);
		assertEquals(customerDTO.getCompany(), COMPANY);
		assertEquals(customerDTO.getEmail(), EMAIL);
		assertEquals(customerDTO.getPhone(), PHONE);
		assertEquals(customerDTO.getFax(), FAX);
		assertEquals(customerDTO.getWebsite(), WEBSITE);
	}

}
