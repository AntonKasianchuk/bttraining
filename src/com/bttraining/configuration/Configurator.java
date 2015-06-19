package com.bttraining.configuration;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;

public class Configurator {

	public static BraintreeGateway getBraintreeGateway() {
		return new BraintreeGateway(Environment.SANDBOX, "your_merchant_id",
				"your_public_key", "your_private_key");
	}
}
