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

import com.jtorres.springexercisecrmaven.entity.Customer;
import com.jtorres.springexercisecrmaven.entity.Rulesheet;
import com.jtorres.springexercisecrmaven.filepayload.UploadFileResponse;
import com.jtorres.springexercisecrmaven.service.CService;
import com.jtorres.springexercisecrmaven.validation.FilenameValidation;

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
	public Rulesheet saveRulesheet(@RequestParam("file") MultipartFile file) {
		
		
		// we will receive the file and save it
		
		// check if the file name is valid
		// if so, enter the try clause for saving
		Rulesheet rulesheet = new Rulesheet();

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println(fileName);
	
		// check if filename is valid
		FilenameValidation fv = new FilenameValidation();
	
		if (fv.IsRegexFilenameOk(fileName)) {
			try {

				// at this point, we can assume the data is ok
				
				
				
				// extract type, customerid, filecontent
				fileName = fileName.replace(".txt", "");
				String[] split_result = fileName.split("_");
				String type = split_result[0];

				int customer_id = Integer.parseInt(split_result[1]);
				
				
				if(!fv.doesCustomerExist(customer_id, service.getCustomers())) {
					System.out.println("customer no exist");
					return rulesheet;
				}

				System.out.println("customer exists");
				rulesheet.setType(type);
				rulesheet.setcustomerId(customer_id);
				rulesheet.setFilecontent(file.getBytes());

				System.out.println("the rulesheet:");
				System.out.println(rulesheet.toString());
				
				rulesheet.setId(0); // save new rulesheet, not update 
				
				service.saveRulesheet(rulesheet);
				return rulesheet;


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return rulesheet;

			}

		} else {
			// invalid file name
			System.out.println("not ok");
			return rulesheet;

		}
		
	
	}
	
	
	
}
