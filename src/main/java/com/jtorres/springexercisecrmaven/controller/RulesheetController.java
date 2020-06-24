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
		System.out.println("here at show for mrulesheet");
		
		Rulesheet therulesheet = new Rulesheet();
		
		theModel.addAttribute("rulesheet", therulesheet);


		return "rulesheet-form";
	}
	
	
	@PostMapping("/processRulesheet")
	public String saveRulesheet(@Valid Rulesheet therulesheet, 
			BindingResult theBindingResult, Model theModel) {
		System.out.println("about to save rulesheet");

		if (theBindingResult.hasErrors()) {
			System.out.println("if statement");
			return "rulesheet-form";
		} else {
			System.out.println("else statement");

			// from the type info, parse the type and customerID
			// we are assured that the customerID exists at this point
			// filecontent is already saved
			
			String type_id = therulesheet.getFilename();
			String[] split_result = type_id.split("_");
			
			String type = split_result[0];
			int customer_id = Integer.parseInt(split_result[1]);
			
			System.out.println("the rulesheet model before:");
			System.out.println(therulesheet.toString());
			
			therulesheet.setType(type);
			therulesheet.setcustomerId(customer_id);
			
			System.out.println("the rulesheet model after:");
			System.out.println(therulesheet.toString());

			service.saveRulesheet(therulesheet);
		
			return "redirect:/rulesheet/list";
			
		}
		
		
	}
	
	
	
}
