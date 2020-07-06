package com.jtorres.springexercisecrmaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jtorres.springexercisecrmaven.entity.Customer;
import com.jtorres.springexercisecrmaven.service.CService;
import com.jtorres.springexercisecrmaven.validation.CustomerValidation;


@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CService service;

	
	// get 1 customer
	@GetMapping(path="/customer/{id}")
	public ResponseEntity<String> getOneCustomer(@PathVariable("id") int cid) {

		System.out.println("CustomerID: " + cid);
		
		// get all customers
		// iterate through one of them
		// return if true
		
		CustomerValidation cv = new CustomerValidation();
		
		if (cv.doesCustomerExist(cid, service.getCustomers())){
			return new ResponseEntity<>("Customer exists!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed: Customer does not exist", HttpStatus.BAD_REQUEST);
		}

	}
	
	// get all customers
	@GetMapping("/customers")
	public List<Customer> getCustomers(){

		
		return service.getCustomers();		
	}
	
	@PostMapping("/customer")
	public ResponseEntity<String> saveCustomer(@RequestParam("name") String name) {

		Customer newCustomer = new Customer();
		
		newCustomer.setId(0); // new record
		newCustomer.setName(name);
		
		service.saveCustomer(newCustomer);
		System.out.println("customer has been saved: " + name);

		return new ResponseEntity<>("Customer " + name + " saved", HttpStatus.OK);
	}
	
	@DeleteMapping(path="/customer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int cid){
		
		// get the customer in the db
		// delete that customer
		
		CustomerValidation cv = new CustomerValidation();
		
		System.out.println("entered here");
		if (cv.doesCustomerExist(cid, service.getCustomers())){
			// we're guaranteed it exists
			System.out.println("entered here yii");
			for (Customer c : service.getCustomers()) {
				if (c.getId() == cid) {
					// match!
					System.out.println("coup de grace");

					service.deleteCustomer(c);
					return new ResponseEntity<>("Customer deleted!", HttpStatus.OK);
				}
			}
			
			
		} else {
			return new ResponseEntity<>("Failed: Customer does not exist", HttpStatus.BAD_REQUEST);
		}
		return null;
		
		
		
	}
}
