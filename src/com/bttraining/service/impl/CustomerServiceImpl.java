package com.bttraining.service.impl;

import java.util.HashSet;
import java.util.Set;

import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.ResourceCollection;
import com.braintreegateway.Result;
import com.bttraining.dao.CustomerDao;
import com.bttraining.dao.impl.CustomerDaoImpl;
import com.bttraining.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao = new CustomerDaoImpl();

	@Override
	public Result<Customer> createCustomer(CustomerRequest customerRequest) {
		Result<Customer> result = customerDao.createCustomer(customerRequest);
		return result;
	}

	@Override
	public Result<Customer> updateCustomer(String customerId,
			CustomerRequest customerRequest) {
		Result<Customer> result = customerDao.updateCustomer(customerId, customerRequest);
		return result;
	}

	private ResourceCollection<Customer> getAllCustomers() {
		ResourceCollection<Customer> customers = customerDao.getAllCustomers();
		return customers;
	}

	@Override
	public Set<String> getCustomerIds() {
		ResourceCollection<Customer> customers = getAllCustomers();
		Set<String> customerIds = new HashSet<String>();
		for (Customer customer : customers) {
			customerIds.add(customer.getId());
		}
		return customerIds;
	}

	@Override
	public ResourceCollection<Customer> getCustomerById(String customerId) {
		ResourceCollection<Customer> customers = customerDao
				.getCustomerById(customerId);
		return customers;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
}
