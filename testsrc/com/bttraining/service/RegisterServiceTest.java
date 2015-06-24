package com.bttraining.service;

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
import com.braintreegateway.ClientTokenGateway;
import com.braintreegateway.ClientTokenRequest;
import com.bttraining.service.impl.RegisterServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RegisterServiceImpl.class)
public class RegisterServiceTest {

	private static final String CUSTOMER_ID = "TEST_CUSTOMER_ID";
	private static final String GENERATED_CLIENT_TOKEN = "TEST_CLIENT_TOKEN";

	@Mock
	private BraintreeGateway gateway;
	@InjectMocks
	private RegisterService registerService = new RegisterServiceImpl();

	@Test
	public void shouldGetClientTokenByCustomer() throws Exception {
		// given
		ClientTokenRequest clientTokenRequest = mock(ClientTokenRequest.class);
		PowerMockito.whenNew(ClientTokenRequest.class).withNoArguments()
				.thenReturn(clientTokenRequest);
		when(clientTokenRequest.customerId(CUSTOMER_ID)).thenReturn(
				clientTokenRequest);
		ClientTokenGateway clientTokenGateway = mock(ClientTokenGateway.class);
		when(gateway.clientToken()).thenReturn(clientTokenGateway);
		when(clientTokenGateway.generate(clientTokenRequest)).thenReturn(
				GENERATED_CLIENT_TOKEN);

		// when
		String clientToken = registerService
				.getClientTokenByCustomer(CUSTOMER_ID);

		// then
		Assert.assertEquals(GENERATED_CLIENT_TOKEN, clientToken);
	}
}
