package com.bttraining.dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerGateway;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.CustomerSearchRequest;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.braintreegateway.TextNode;
import com.bttraining.dao.impl.CustomerDAOImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CustomerDAOImpl.class)
public class CustomerDAOTest {

	private static final String CUSTOMER_ID = "TEST_CUSTOMER_ID";
	private static final String EMPTY_CUSTOMER_ID = "";

	@Mock
	private BraintreeGateway gateway;
	@InjectMocks
	private CustomerDAO ñustomerDao = new CustomerDAOImpl();

	@Test
	public void shouldCreateCustomer() {
		// given
		CustomerRequest customerRequest = mock(CustomerRequest.class);
		@SuppressWarnings("unchecked")
		Result<Customer> expectedResult = (Result<Customer>) mock(Result.class);
		CustomerGateway customerGateway = mock(CustomerGateway.class);
		when(gateway.customer()).thenReturn(customerGateway);
		when(customerGateway.create(customerRequest)).thenReturn(expectedResult);

		// when
		Result<Customer> actualResult = ñustomerDao.createCustomer(customerRequest);

		// then
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldUpdateCustomer() {
		// given
		CustomerRequest customerRequest = mock(CustomerRequest.class);
		@SuppressWarnings("unchecked")
		Result<Customer> expectedResult = (Result<Customer>) mock(Result.class);
		CustomerGateway customerGateway = mock(CustomerGateway.class);
		when(gateway.customer()).thenReturn(customerGateway);
		when(customerGateway.update(CUSTOMER_ID,customerRequest)).thenReturn(expectedResult);

		// when
		Result<Customer> actualResult = ñustomerDao.updateCustomer(CUSTOMER_ID, customerRequest);

		// then
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldRetrieveAllCustomers() throws Exception {
		// given
		CustomerSearchRequest customerSearchRequest = mock(CustomerSearchRequest.class);
		PowerMockito.whenNew(CustomerSearchRequest.class).withNoArguments()
				.thenReturn(customerSearchRequest);
		@SuppressWarnings("unchecked")
		TextNode<CustomerSearchRequest> customerSearchRequestTextNode = (TextNode<CustomerSearchRequest>) mock(TextNode.class);
		when(customerSearchRequest.id()).thenReturn(
				customerSearchRequestTextNode);
		when(customerSearchRequestTextNode.is(EMPTY_CUSTOMER_ID)).thenReturn(
				customerSearchRequest);
		CustomerGateway customerGateway = mock(CustomerGateway.class);
		when(gateway.customer()).thenReturn(customerGateway);
		@SuppressWarnings("unchecked")
		ResourceCollection<Customer> expectedResult = (ResourceCollection<Customer>) mock(ResourceCollection.class);
		when(customerGateway.search(customerSearchRequest)).thenReturn(
				expectedResult);

		// when
		ResourceCollection<Customer> actualResult = ñustomerDao
				.getAllCustomers();

		// then
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void shouldRetrieveCustomerById() throws Exception {
		// given
		CustomerSearchRequest customerSearchRequest = mock(CustomerSearchRequest.class);
		PowerMockito.whenNew(CustomerSearchRequest.class).withNoArguments()
				.thenReturn(customerSearchRequest);
		@SuppressWarnings("unchecked")
		TextNode<CustomerSearchRequest> customerSearchRequestTextNode = (TextNode<CustomerSearchRequest>) mock(TextNode.class);
		when(customerSearchRequest.id()).thenReturn(
				customerSearchRequestTextNode);
		when(customerSearchRequestTextNode.is(CUSTOMER_ID)).thenReturn(
				customerSearchRequest);
		CustomerGateway customerGateway = mock(CustomerGateway.class);
		when(gateway.customer()).thenReturn(customerGateway);
		@SuppressWarnings("unchecked")
		ResourceCollection<Customer> expectedResult = (ResourceCollection<Customer>) mock(ResourceCollection.class);
		when(customerGateway.search(customerSearchRequest)).thenReturn(
				expectedResult);

		// when
		ResourceCollection<Customer> actualResult = ñustomerDao
				.getCustomerById(CUSTOMER_ID);

		// then
		Assert.assertEquals(expectedResult, actualResult);
	}
}
