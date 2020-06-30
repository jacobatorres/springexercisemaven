package com.jtorres.springexercisecrmaven.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jtorres.springexercisecrmaven.entity.Customer;
import com.jtorres.springexercisecrmaven.entity.Rulesheet;
import com.jtorres.springexercisecrmaven.filepayload.UploadFileResponse;
import com.jtorres.springexercisecrmaven.service.CService;
import com.jtorres.springexercisecrmaven.validation.CustomerValidation;

@RestController
@RequestMapping("/rulesheet")
public class RulesheetController {

	@Autowired
	private CService service;
	
	
	@PostMapping("/rulesheet")
	public ResponseEntity<String> saveRulesheet(@RequestParam("file") MultipartFile file) {
		
		
		// we will receive the file and save it
		
		// check if the file name is valid
		// if so, enter the try clause for saving
		Rulesheet rulesheet = new Rulesheet();

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	
		// check if filename is valid
		CustomerValidation fv = new CustomerValidation();
	
		if (fv.IsRegexFilenameOk(fileName)) {
			try {

				// at this point, we can assume the data is ok
				
				
				
				// extract type, customerid, filecontent
				fileName = fileName.replace(".txt", "");
				String[] split_result = fileName.split("_");
				String type = split_result[0];

				int customer_id = Integer.parseInt(split_result[1]);
				
				
				if(!fv.doesCustomerExist(customer_id, service.getCustomers())) {
					return new ResponseEntity<>("Failed: Customer in file name does not exist", HttpStatus.OK);
				}

				rulesheet.setType(type);
				rulesheet.setcustomerId(customer_id);
				rulesheet.setFilecontent(file.getBytes());
				rulesheet.setId(0); // save new rulesheet, not update 
				
				service.saveRulesheet(rulesheet);
				return new ResponseEntity<>("Rulesheet " + fileName +  ".txt uploaded!", HttpStatus.OK);


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<>("Failed: entered IOException Catch", HttpStatus.OK);

			}

		} else {
			// invalid file name
			return new ResponseEntity<>("Failed: Rule does not look like ruleA_12.txt", HttpStatus.OK);

		}
		
	
	}
	
	
	
}
