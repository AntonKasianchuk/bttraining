package com.bttraining.configuration;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;

public class Configurator {

    public static BraintreeGateway getBraintreeGateway() {
        return new BraintreeGateway(
            Environment.SANDBOX,
            "vbjt6q2zp8nq4vgh",
            "7zyy96mftydc47sp",
            "2a48f4b9a1ca941a4eec76eaca037d8e");
    }
}
