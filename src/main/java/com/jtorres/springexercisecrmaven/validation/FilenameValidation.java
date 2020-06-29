package com.jtorres.springexercisecrmaven.validation;

import java.util.List;
import java.util.regex.Pattern;

import com.jtorres.springexercisecrmaven.entity.Customer;

public class FilenameValidation {
	
	public boolean IsRegexFilenameOk(String filename) {
		
		// check if filename ends in .txt
		System.out.println(filename);

		if (filename.endsWith(".txt") == false) {
			return false;
		}
		
		System.out.println("it ends in txt congrats");
	    filename = filename.replace(".txt", "");
	    
		boolean isMatchingConvention = Pattern.matches("^[a-zA-z0-9]+_[0-9]+", filename);

	    if (isMatchingConvention == false) {
	    	return false;
	    }
		System.out.println("siyang tunay");

	    return true;
		
	}
	
	public boolean doesCustomerExist(int cid, List<Customer> theCustomers) {
		
		for (int i = 0; i < theCustomers.size(); i++) {
			int id_rightnow = theCustomers.get(i).getId();
			if (id_rightnow  == cid) { // match!
				return true;
			} 
		}
	
		return false;
	}


}
