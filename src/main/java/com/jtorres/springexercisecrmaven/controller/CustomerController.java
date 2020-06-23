package com.jtorres.springexercisecrmaven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jtorres.springexercisecrmaven.entity.Customer;
import com.jtorres.springexercisecrmaven.service.CService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CService service;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		System.out.println("musta list customers!");

		List<Customer> theCustomers = service.getCustomers();
		
		theModel.addAttribute("customers", theCustomers);
		
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd") 
	public String ShowAddCustomerPage(Model theModel) {
			
		Customer thecustomer = new Customer();
		
		theModel.addAttribute("customer", thecustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		System.out.println("musta");
		
		System.out.println(theCustomer.getName());
		
		service.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
}
