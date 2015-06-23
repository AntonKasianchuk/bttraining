package com.bttraining.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.bttraining.dao.CustomerDao;
import com.bttraining.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@Mock
	private CustomerDao customerDao;

	@InjectMocks
	private CustomerService customerService = new CustomerServiceImpl();

	@Before
	public void setUp() {
		// if the test case cannot be run with Mockito JUnitRunner
		//MockitoAnnotations.initMocks(this);
	}

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
		String customerId = "id";
		@SuppressWarnings("unchecked")
		Result<Customer> expectedResult = (Result<Customer>) mock(Result.class);
		CustomerRequest customerRequest = mock(CustomerRequest.class);
		when(customerDao.updateCustomer(customerId, customerRequest))
				.thenReturn(expectedResult);

		// when
		Result<Customer> actualResult = customerService.updateCustomer(
				customerId, customerRequest);

		// then
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getCustomerById() {
		// given
		String customerId = "id";
		@SuppressWarnings("unchecked")
		ResourceCollection<Customer> expectedResult = (ResourceCollection<Customer>) mock(ResourceCollection.class);
		when(customerDao.getCustomerById(customerId))
				.thenReturn(expectedResult);

		// when
		ResourceCollection<Customer> actualResult = customerService
				.getCustomerById(customerId);

		// then
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldGetCustomerIds() {
		// given
		String[] expectedIds = new String[] { "123", "abc", "4d5e6f" };
		List<Customer> customerList = generateCustomerListByIds(expectedIds);
		@SuppressWarnings("unchecked")
		ResourceCollection<Customer> resourceCollection = (ResourceCollection<Customer>) mock(ResourceCollection.class);
		when(customerDao.getAllCustomers()).thenReturn(resourceCollection);
		when(resourceCollection.iterator()).thenReturn(customerList.iterator());

		// when
		Set<String> actualIdSet = customerService.getCustomerIds();

		// then
		Set<String> expectedIdSet = new HashSet<>(Arrays.asList(expectedIds));
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
