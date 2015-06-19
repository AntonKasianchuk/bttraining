package com.bttraining.facade.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

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
	private CustomerInfoDTO expectedCustomerInfoDTO;
	
	private final CustomerConverter unit = new CustomerConverter();

	@Test
	public void shouldConvertCustomerToCustomerDTO() {
		Customer customer = Mockito.mock(Customer.class);
		Mockito.when(customer.getFirstName()).thenReturn(FIRST_NAME);
		Mockito.when(customer.getLastName()).thenReturn(LAST_NAME);
		Mockito.when(customer.getCompany()).thenReturn(COMPANY);
		Mockito.when(customer.getEmail()).thenReturn(EMAIL);
		Mockito.when(customer.getPhone()).thenReturn(PHONE);
		Mockito.when(customer.getFax()).thenReturn(FAX);
		Mockito.when(customer.getWebsite()).thenReturn(WEBSITE);
		CustomerInfoDTO customerDTO = unit.generateCustomerDTO(customer);
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
		CustomerInfoDTO customerInfoDTO = Mockito.mock(CustomerInfoDTO.class);
		CustomerRequest customerRequest = unit.generateCustomerRequest(customerInfoDTO);
		Mockito.verify(customerInfoDTO).getFirstName();
		Mockito.verify(customerInfoDTO).getLastName();
		Mockito.verify(customerInfoDTO).getCompany();
		Mockito.verify(customerInfoDTO).getEmail();
		Mockito.verify(customerInfoDTO).getPhone();
		Mockito.verify(customerInfoDTO).getFax();
		Mockito.verify(customerInfoDTO).getWebsite();
		assertNotNull(customerRequest);
	}

	@Test
	public void shouldConvertParametersMapToCustomerDTO() {
		@SuppressWarnings("unchecked")
		Map<String, String[]> customerParameterMap = Mockito.mock(Map.class);
		Mockito.when(customerParameterMap.get("first_name")).thenReturn(new String[]{FIRST_NAME});
		Mockito.when(customerParameterMap.get("last_name")).thenReturn(new String[]{LAST_NAME});
		Mockito.when(customerParameterMap.get("company")).thenReturn(new String[]{COMPANY});
		Mockito.when(customerParameterMap.get("email")).thenReturn(new String[]{EMAIL});
		Mockito.when(customerParameterMap.get("phone")).thenReturn(new String[]{PHONE});
		Mockito.when(customerParameterMap.get("fax")).thenReturn(new String[]{FAX});
		Mockito.when(customerParameterMap.get("website")).thenReturn(new String[]{WEBSITE});
		
		CustomerInfoDTO customerDTO = unit.generateCustomerDTO(customerParameterMap);
		assertEquals(customerDTO.getFirstName(), FIRST_NAME);
		assertEquals(customerDTO.getLastName(), LAST_NAME);
		assertEquals(customerDTO.getCompany(), COMPANY);
		assertEquals(customerDTO.getEmail(), EMAIL);
		assertEquals(customerDTO.getPhone(), PHONE);
		assertEquals(customerDTO.getFax(), FAX);
		assertEquals(customerDTO.getWebsite(), WEBSITE);
	}

}
