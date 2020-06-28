package com.jtorres.springexercisecrmaven.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jtorres.springexercisecrmaven.entity.Rulesheet;
import com.jtorres.springexercisecrmaven.filepayload.UploadFileResponse;
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
	
	@GetMapping("/uploadfileform")
	public String showUploadForm() {
		return "file-upload-form";
	}
	 
	@GetMapping("/showFormAddRulesheet") 
	public String showFormAddRulesheet(Model theModel) {
		System.out.println("[/showFormAddRulesheet GET] Displaying the rulesheet form");
		
		Rulesheet therulesheet = new Rulesheet();
		
		theModel.addAttribute("rulesheet", therulesheet);


		return "rulesheet-form";
	}
	
	
	@PostMapping("/processRulesheet")
	public String saveRulesheet(@RequestParam("file") MultipartFile file) {
		
		
		// we will receive the file and save it
		
		
		// store the file
		// send a response saying it's good
		try {
			Rulesheet rulesheet = new Rulesheet();


			// extract type, customerid, filecontent
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			fileName = fileName.replace(".txt", "");
			
			String[] split_result = fileName.split("_");
			String type = split_result[0];
			int customer_id = Integer.parseInt(split_result[1]);

			rulesheet.setType(type);
			rulesheet.setcustomerId(customer_id);
			rulesheet.setFilecontent(file.getBytes());

			System.out.println("the rulesheet:");
			System.out.println(rulesheet.toString());
			
			rulesheet.setId(0); // save new rulesheet, not update 
			
			service.saveRulesheet(rulesheet);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		
//		
//		String type_id = rulesheet.getFilename();
//		String[] split_result = type_id.split("_");
//		
//		String type = split_result[0];
//		int customer_id = Integer.parseInt(split_result[1]);
//		
//		System.out.println("the rulesheet model before:");
//		System.out.println(rulesheet.toString());
//		
//		rulesheet.setType(type);
//		rulesheet.setcustomerId(customer_id);
//		rulesheet.setId(0); // save rulesheet
//		
//		System.out.println("the rulesheet model after:");
//		System.out.println(rulesheet.toString());
//
//		service.saveRulesheet(rulesheet);
//	
		return "redirect:/rulesheet/list";
		

		
		
	}
	
	
	
}
