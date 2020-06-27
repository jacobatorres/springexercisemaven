package com.jtorres.springexercisecrmaven.service;

import java.util.List;

import javax.validation.Valid;

import com.jtorres.springexercisecrmaven.entity.Customer;
import com.jtorres.springexercisecrmaven.entity.Rulesheet;


public interface CService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public List<Rulesheet> getRulesheets();

	public void saveRulesheet(Rulesheet therulesheet);
	
	
}
