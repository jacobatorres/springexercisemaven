package com.jtorres.springexercisecrmaven.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jtorres.springexercisecrmaven.entity.Rulesheet;
import com.jtorres.springexercisecrmaven.service.CService;

@Controller
@RequestMapping("/rulesheet")
public class RulesheetController {

	@Autowired
	private CService service;
	
	@GetMapping("/list")
	public String showPage(Model theModel) {
		
		// get rulesheets
		List<Rulesheet> rulesheets = service.getRulesheets();
		
		// add to model
		
		theModel.addAttribute("rulesheets", rulesheets);
		
		return "list-rulesheet";
	}
	
	
	@GetMapping("/showFormAddRulesheet") 
	public String showFormAddRulesheet(Model theModel) {
		System.out.println("[/showFormAddRulesheet GET] Displaying the rulesheet form");
		
		Rulesheet therulesheet = new Rulesheet();
		
		theModel.addAttribute("rulesheet", therulesheet);


		return "rulesheet-form";
	}
	
	
	@PostMapping("/processRulesheet")
	public String saveRulesheet(@ModelAttribute("rulesheet") @Valid Rulesheet rulesheet, 
			BindingResult theBindingResult, Model theModel) {
		
		System.out.println("[/processRulesheet POST ] Saving the rulesheet");
		System.out.println(theModel.asMap());

		if (theBindingResult.hasErrors()) {
			System.out.println("Determined errors!");
			return "rulesheet-form";
		} else {
			System.out.println("No Errors apparently! Not Good!");

			// from the type info, parse the type and customerID
			// we are assured that the customerID exists at this point
			// filecontent is already saved
			
			String type_id = rulesheet.getFilename();
			String[] split_result = type_id.split("_");
			
			String type = split_result[0];
			int customer_id = Integer.parseInt(split_result[1]);
			
			System.out.println("the rulesheet model before:");
			System.out.println(rulesheet.toString());
			
			rulesheet.setType(type);
			rulesheet.setcustomerId(customer_id);
			
			System.out.println("the rulesheet model after:");
			System.out.println(rulesheet.toString());

			service.saveRulesheet(rulesheet);
		
			return "redirect:/rulesheet/list";
			
		}
		
		
	}
	
	
	
}
