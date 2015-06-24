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
import com.bttraining.facade.impl.TransactionFacadeImpl;
import com.bttraining.service.CustomerService;
import com.bttraining.service.RegisterService;
import com.bttraining.web.dto.CustomerDTO;
import com.bttraining.web.dto.CustomerInfoDTO;
import com.bttraining.web.dto.CustomerRegisterDTO;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CustomerFacadeImpl.class)
public class CustomerFacadeTest {

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
		String[] expectedCustomerIds = { "123", "456", "1a2b3c" };
		Set<String> expectedCustomerIdSet = new HashSet<>(
				Arrays.asList(expectedCustomerIds));
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
		String customerId = "id";
		@SuppressWarnings("unchecked")
		ResourceCollection<Customer> resourceCustomers = (ResourceCollection<Customer>) mock(ResourceCollection.class);
		when(customerService.getCustomerById(customerId)).thenReturn(
				resourceCustomers);
		Customer customer = mock(Customer.class);
		when(resourceCustomers.getFirst()).thenReturn(customer);
		when(customerConverter.generateCustomerDTO(customer)).thenReturn(
				expectedCustomerInfoDTO);

		// when
		CustomerInfoDTO actualCustomerInfoDTO = customerFacade
				.getCustomerDTOById(customerId);

		// then
		assertEquals(expectedCustomerInfoDTO, actualCustomerInfoDTO);
	}

	@Test
	public void shouldGetClientTokenByCustomerId() {
		// given
		String customerId = "id";
		String expectedClientToken = "client_token";
		@SuppressWarnings("unchecked")
		ResourceCollection<Customer> resourceCustomers = (ResourceCollection<Customer>) mock(ResourceCollection.class);
		when(customerService.getCustomerById(customerId)).thenReturn(
				resourceCustomers);
		Customer customer = mock(Customer.class);
		when(resourceCustomers.getFirst()).thenReturn(customer);
		when(customer.getId()).thenReturn(customerId);
		when(registerService.getClientTokenByCustomer(customerId)).thenReturn(
				expectedClientToken);

		// when
		String actualClientToken = customerFacade
				.getClientTokenByCustomerId(customerId);

		// then
		assertEquals(expectedClientToken, actualClientToken);
	}

	@Test
	public void shouldUpdateCustomer() throws Exception {
		// given
		String customerId = "id";
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
		when(customerService.updateCustomer(customerId, customerRequest))
				.thenReturn(customerResult);

		// when
		CustomerDTO actualCustomerDTO = customerFacade.updateCustomer(
				customerId, customerParameterMap);

		// then
		verify(expectedCustomerDTO).setCustomerInfoDTO(customerInfoDTO);
		verify(expectedCustomerDTO).setSuccess(SUCCESS);
		assertEquals(expectedCustomerDTO, actualCustomerDTO);
	}

	@Test
	public void shouldCreateCustomer() throws Exception {
		// given
		String customerId = "id";
		String clientToken = "client_token";
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
		when(customer.getId()).thenReturn(customerId);
		when(registerService.getClientTokenByCustomer(customerId)).thenReturn(
				clientToken);
		when(customerService.createCustomer(customerRequest)).thenReturn(
				customerResult);

		// when
		CustomerRegisterDTO actualCustomerRegisterDTO = customerFacade
				.createCustomer(customerParameterMap);

		// then
		verify(expectedCustomerRegisterDTO).setSuccess(SUCCESS);
		verify(expectedCustomerRegisterDTO).setClientToken(clientToken);
		assertEquals(expectedCustomerRegisterDTO, actualCustomerRegisterDTO);
	}
}
