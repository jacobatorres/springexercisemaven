package com.jtorres.springexercisecrmaven.DAO;

import java.util.List;

import com.jtorres.springexercisecrmaven.entity.Customer;


public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public void deleteCustomer(Customer theCustomer);


}
