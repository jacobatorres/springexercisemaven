package com.jtorres.springexercisecrmaven.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CustomerExistConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface DoesCustomerExist {
	
	// default values
	// not really needed
	
	// default error message
	public String message() default "Customer does not exist";
	
	// default groups
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
	
	
	
	
	
}
