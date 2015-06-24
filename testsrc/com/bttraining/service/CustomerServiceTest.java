package com.bttraining.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.bttraining.dao.CustomerDAO;
import com.bttraining.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	private static final String CUSTOMER_ID = "TEST_CUSTOMER_ID";
	private static final String[] CUSTOMER_IDS = { "TEST_CUSTOMER_ID1",
			"TEST_CUSTOMER_ID2", "TEST_CUSTOMER_ID3" };

	@Mock
	private CustomerDAO customerDao;

	@InjectMocks
	private CustomerService customerService = new CustomerServiceImpl();

	@Test
	public void shouldCreateCustomer() {
		// given
		@SuppressWarnings("unchecked")
		Result<Customer> expectedResult = (Result<Customer>) mock(Result.class);
		CustomerRequest customerRequest = mock(CustomerRequest.class);
		when(customerDao.createCustomer(customerRequest)).thenReturn(
				expectedResult);

		// when
		Result<Customer> actualResult = customerService
				.createCustomer(customerRequest);

		// then
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldUpdateCustomer() {
		// given
		@SuppressWarnings("unchecked")
		Result<Customer> expectedResult = (Result<Customer>) mock(Result.class);
		CustomerRequest customerRequest = mock(CustomerRequest.class);
		when(customerDao.updateCustomer(CUSTOMER_ID, customerRequest))
				.thenReturn(expectedResult);

		// when
		Result<Customer> actualResult = customerService.updateCustomer(
				CUSTOMER_ID, customerRequest);

		// then
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getCustomerById() {
		// given
		@SuppressWarnings("unchecked")
		ResourceCollection<Customer> expectedResult = (ResourceCollection<Customer>) mock(ResourceCollection.class);
		when(customerDao.getCustomerById(CUSTOMER_ID)).thenReturn(
				expectedResult);

		// when
		ResourceCollection<Customer> actualResult = customerService
				.getCustomerById(CUSTOMER_ID);

		// then
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldGetCustomerIds() {
		// given
		List<Customer> customerList = generateCustomerListByIds(CUSTOMER_IDS);
		@SuppressWarnings("unchecked")
		ResourceCollection<Customer> resourceCollection = (ResourceCollection<Customer>) mock(ResourceCollection.class);
		when(customerDao.getAllCustomers()).thenReturn(resourceCollection);
		when(resourceCollection.iterator()).thenReturn(customerList.iterator());

		// when
		Set<String> actualIdSet = customerService.getCustomerIds();

		// then
		Set<String> expectedIdSet = new HashSet<>(Arrays.asList(CUSTOMER_IDS));
		Assert.assertEquals(expectedIdSet, actualIdSet);
	}

	private List<Customer> generateCustomerListByIds(String[] ids) {
		List<Customer> customerList = new ArrayList<>(ids.length);
		for (String id : ids) {
			Customer customer = createCustomerMockAndSetBehaviour(id);
			customerList.add(customer);
		}
		return customerList;
	}

	private Customer createCustomerMockAndSetBehaviour(String id) {
		Customer customer = mock(Customer.class);
		when(customer.getId()).thenReturn(id);
		return customer;
	}
}
