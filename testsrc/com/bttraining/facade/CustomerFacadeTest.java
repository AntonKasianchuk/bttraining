package com.bttraining.facade;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.bttraining.facade.converter.CustomerConverter;
import com.bttraining.facade.impl.CustomerFacadeImpl;
import com.bttraining.service.CustomerService;
import com.bttraining.service.RegisterService;
import com.bttraining.web.dto.CustomerDTO;
import com.bttraining.web.dto.CustomerInfoDTO;
import com.bttraining.web.dto.CustomerRegisterDTO;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CustomerFacadeImpl.class)
public class CustomerFacadeTest {

	private static final String CUSTOMER_ID = "TEST_CUSTOMER_ID";
	private static final String[] CUSTOMER_IDS = { "TEST_CUSTOMER_ID1",
			"TEST_CUSTOMER_ID2", "TEST_CUSTOMER_ID3" };
	private static final String GENERATED_CLIENT_TOKEN = "TEST_CLIENT_TOKEN";
	private static final boolean SUCCESS = true;

	@Mock
	private CustomerService customerService;
	@Mock
	private RegisterService registerService;
	@Mock
	private CustomerConverter customerConverter;

	@InjectMocks
	CustomerFacade customerFacade = new CustomerFacadeImpl();

	@Test
	public void shouldGetCustomerIds() {
		// given
		Set<String> expectedCustomerIdSet = new HashSet<>(
				Arrays.asList(CUSTOMER_IDS));
		when(customerService.getCustomerIds())
				.thenReturn(expectedCustomerIdSet);

		// when
		Set<String> actualCustomerIdSet = customerFacade.getCustomerIds();

		// then
		assertEquals(expectedCustomerIdSet, actualCustomerIdSet);
	}

	@Test
	public void shouldGetCustomerDTOById() {
		// given
		CustomerInfoDTO expectedCustomerInfoDTO = mock(CustomerInfoDTO.class);
		@SuppressWarnings("unchecked")
		ResourceCollection<Customer> resourceCustomers = (ResourceCollection<Customer>) mock(ResourceCollection.class);
		when(customerService.getCustomerById(CUSTOMER_ID)).thenReturn(
				resourceCustomers);
		Customer customer = mock(Customer.class);
		when(resourceCustomers.getFirst()).thenReturn(customer);
		when(customerConverter.generateCustomerDTO(customer)).thenReturn(
				expectedCustomerInfoDTO);

		// when
		CustomerInfoDTO actualCustomerInfoDTO = customerFacade
				.getCustomerDTOById(CUSTOMER_ID);

		// then
		assertEquals(expectedCustomerInfoDTO, actualCustomerInfoDTO);
	}

	@Test
	public void shouldGetClientTokenByCustomerId() {
		// given
		@SuppressWarnings("unchecked")
		ResourceCollection<Customer> resourceCustomers = (ResourceCollection<Customer>) mock(ResourceCollection.class);
		when(customerService.getCustomerById(CUSTOMER_ID)).thenReturn(
				resourceCustomers);
		Customer customer = mock(Customer.class);
		when(resourceCustomers.getFirst()).thenReturn(customer);
		when(customer.getId()).thenReturn(CUSTOMER_ID);
		when(registerService.getClientTokenByCustomer(CUSTOMER_ID)).thenReturn(
				GENERATED_CLIENT_TOKEN);

		// when
		String actualClientToken = customerFacade
				.getClientTokenByCustomerId(CUSTOMER_ID);

		// then
		assertEquals(GENERATED_CLIENT_TOKEN, actualClientToken);
	}

	@Test
	public void shouldUpdateCustomer() throws Exception {
		// given
		@SuppressWarnings("unchecked")
		Map<String, String[]> customerParameterMap = (Map<String, String[]>) mock(HashMap.class);
		CustomerDTO expectedCustomerDTO = mock(CustomerDTO.class);
		whenNew(CustomerDTO.class).withNoArguments().thenReturn(
				expectedCustomerDTO);
		CustomerInfoDTO customerInfoDTO = mock(CustomerInfoDTO.class);
		when(customerConverter.generateCustomerDTO(customerParameterMap))
				.thenReturn(customerInfoDTO);
		CustomerRequest customerRequest = mock(CustomerRequest.class);
		when(customerConverter.generateCustomerRequest(customerInfoDTO))
				.thenReturn(customerRequest);
		@SuppressWarnings("unchecked")
		Result<Customer> customerResult = (Result<Customer>) mock(Result.class);
		when(customerResult.isSuccess()).thenReturn(SUCCESS);
		when(customerService.updateCustomer(CUSTOMER_ID, customerRequest))
				.thenReturn(customerResult);

		// when
		CustomerDTO actualCustomerDTO = customerFacade.updateCustomer(
				CUSTOMER_ID, customerParameterMap);

		// then
		verify(expectedCustomerDTO).setCustomerInfoDTO(customerInfoDTO);
		verify(expectedCustomerDTO).setSuccess(SUCCESS);
		assertEquals(expectedCustomerDTO, actualCustomerDTO);
	}

	@Test
	public void shouldCreateCustomer() throws Exception {
		// given
		@SuppressWarnings("unchecked")
		Map<String, String[]> customerParameterMap = (Map<String, String[]>) mock(HashMap.class);
		CustomerRegisterDTO expectedCustomerRegisterDTO = mock(CustomerRegisterDTO.class);
		whenNew(CustomerRegisterDTO.class).withNoArguments().thenReturn(
				expectedCustomerRegisterDTO);

		CustomerInfoDTO customerInfoDTO = mock(CustomerInfoDTO.class);
		when(customerConverter.generateCustomerDTO(customerParameterMap))
				.thenReturn(customerInfoDTO);
		CustomerRequest customerRequest = mock(CustomerRequest.class);
		when(customerConverter.generateCustomerRequest(customerInfoDTO))
				.thenReturn(customerRequest);
		@SuppressWarnings("unchecked")
		Result<Customer> customerResult = (Result<Customer>) mock(Result.class);
		when(customerResult.isSuccess()).thenReturn(SUCCESS);
		Customer customer = mock(Customer.class);
		when(customerResult.getTarget()).thenReturn(customer);
		when(customer.getId()).thenReturn(CUSTOMER_ID);
		when(registerService.getClientTokenByCustomer(CUSTOMER_ID)).thenReturn(
				GENERATED_CLIENT_TOKEN);
		when(customerService.createCustomer(customerRequest)).thenReturn(
				customerResult);

		// when
		CustomerRegisterDTO actualCustomerRegisterDTO = customerFacade
				.createCustomer(customerParameterMap);

		// then
		verify(expectedCustomerRegisterDTO).setSuccess(SUCCESS);
		verify(expectedCustomerRegisterDTO).setClientToken(
				GENERATED_CLIENT_TOKEN);
		assertEquals(expectedCustomerRegisterDTO, actualCustomerRegisterDTO);
	}
}
