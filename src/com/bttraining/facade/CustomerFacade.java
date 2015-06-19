package com.bttraining.facade;

import java.util.Map;
import java.util.Set;

import com.bttraining.web.dto.CustomerDTO;
import com.bttraining.web.dto.CustomerInfoDTO;
import com.bttraining.web.dto.CustomerRegisterDTO;

public interface CustomerFacade {
	Set<String> getCustomerIds();

	CustomerInfoDTO getCustomerDTOById(String customerId);

	CustomerDTO updateCustomer(String customerId,
			Map<String, String[]> customerParameterMap);

	CustomerRegisterDTO createCustomer(
			Map<String, String[]> customerParameterMap);

	String getClientTokenByCustomerId(String customerId);
}
