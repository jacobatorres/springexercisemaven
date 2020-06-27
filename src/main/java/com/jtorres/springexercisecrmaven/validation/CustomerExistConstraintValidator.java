package com.jtorres.springexercisecrmaven.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jtorres.springexercisecrmaven.entity.Customer;
import com.jtorres.springexercisecrmaven.service.CService;


public class CustomerExistConstraintValidator 
	implements ConstraintValidator<DoesCustomerExist, String> {
		
	@Autowired
	private CService service;
	
	
	

	
	@Override
	public boolean isValid(String userInput, ConstraintValidatorContext theConstraintValidatorContext) {
		
		
		// logic here
		
		// get the customerid part
		// fetch the ids from the db
		// check if that value exists there...
				
		String[] split_result = userInput.split("_");
		
		if (split_result.length > 1) {
			int customer_id_given = Integer.parseInt(userInput.split("_")[1]);

			List<Customer> theCustomers = service.getCustomers();
			
			for (int i = 0; i < theCustomers.size(); i++) {
				int id_rightnow = theCustomers.get(i).getId();
				if (id_rightnow  == customer_id_given) { // match!
					return true;
				} 
				
				System.out.println(id_rightnow);
			}
		
			return false;
			
		} else {
			// input does not have _
			// very false
			
			return false;
		}
		
	}

}
