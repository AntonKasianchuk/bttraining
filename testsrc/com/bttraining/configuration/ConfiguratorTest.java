//package com.bttraining.configuration;
//
//import static org.easymock.EasyMock.expect;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.powermock.api.easymock.PowerMock.mockStatic;
//import static org.powermock.api.easymock.PowerMock.replay;
//import static org.powermock.api.easymock.PowerMock.verify;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import com.braintreegateway.BraintreeGateway;
//
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(Configurator.class)
//public class ConfiguratorTest {
//
//	@Test
//	public void shouldReturnBraintreeGateway() {
//		// given
//		mockStatic(Configurator.class);
//		BraintreeGateway expectedBraintreeGateway = mock(BraintreeGateway.class);
//		expect(Configurator.getBraintreeGateway()).andReturn(expectedBraintreeGateway);
//		replay(Configurator.class);
//		// when
//		BraintreeGateway actualBraintreeGateway = Configurator.getBraintreeGateway();
//		// then
//		verify(Configurator.class);
//		assertEquals(expectedBraintreeGateway, actualBraintreeGateway);
//	}
//}
