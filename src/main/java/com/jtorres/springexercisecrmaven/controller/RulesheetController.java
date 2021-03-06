package com.jtorres.springexercisecrmaven.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jtorres.springexercisecrmaven.entity.Customer;
import com.jtorres.springexercisecrmaven.entity.Rulesheet;
import com.jtorres.springexercisecrmaven.security.AuthenticationRequest;
import com.jtorres.springexercisecrmaven.security.AuthenticationResponse;
import com.jtorres.springexercisecrmaven.security.JwtUtil;
import com.jtorres.springexercisecrmaven.security.MyUserDetailsService;
import com.jtorres.springexercisecrmaven.service.CService;
import com.jtorres.springexercisecrmaven.validation.CustomerValidation;

@RestController
@CrossOrigin
public class RulesheetController {

	@Autowired
	private CService service;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	

	// get all rulesheets
	@GetMapping("/rulesheets")
	public List<Rulesheet> getRulesheets(){
		return service.getRulesheets();
	}
	

	@PostMapping(value = "/rulesheet")
	public ResponseEntity<String> saveRulesheet(@RequestParam("file") MultipartFile file) {
		System.out.println("hereees");


		// we will receive the file and save it
		
		// check if the file name is valid
		// if so, enter the try clause for saving
		Rulesheet rulesheet = new Rulesheet();

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		// if the directory name is included, remove it 
		if (fileName.contains("/")) {
			fileName = fileName.substring(fileName.indexOf("/") + 1);
		}
	
		// check if filename is valid
		CustomerValidation fv = new CustomerValidation();
	
		if (fv.IsRegexFilenameOk(fileName)) {
			try {

				// at this point, we can assume the data is ok
				
				
				
				// extract type, customerid, filecontent
				fileName = fileName.replace(".txt", "");
				System.out.println(fileName);
				String[] split_result = fileName.split("_");
				String type = split_result[0];

				int customer_id = Integer.parseInt(split_result[1]);
				
				
				if(!fv.doesCustomerExist(customer_id, service.getCustomers())) {
					return new ResponseEntity<>("Cannot upload " +  fileName + ": Customer in file name does not exist", HttpStatus.OK);
				}

				rulesheet.setType(type);
				rulesheet.setCid(customer_id);
				rulesheet.setFilecontent(file.getBytes());
				rulesheet.setId(0); // save new rulesheet, not update 
				
				service.saveRulesheet(rulesheet);
				return new ResponseEntity<>("Rulesheet " + fileName +  ".txt uploaded!", HttpStatus.OK);


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<>("Cannot upload " + fileName + ": entered IOException Catch", HttpStatus.OK);

			}

		} else {
			// invalid file name
			return new ResponseEntity<>("Cannot upload + " + fileName + ": Rule does not look like ruleA_12.txt", HttpStatus.OK);

		}
		
	
	}
	
	
	@PostMapping("/authenticate" )
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
		// authRequest should have username and password
		// use authManager to authenticate
		System.out.println("attemp!");
		try {
			
			authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			
			
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect credentials", e);
		}
		
		// at this point we know the credentials are valid
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
	
}
