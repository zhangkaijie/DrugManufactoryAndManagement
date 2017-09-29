package com.drugManufactoryAndManagement.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.drugManufactoryAndManagement.spring.pojo.Doctor;
import com.drugManufactoryAndManagement.spring.pojo.Patient;

public class DoctorValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Doctor.class);
	}
	
	 @Override
	    public void validate(Object o, Errors errors) {
	        Doctor d= (Doctor) o;
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "firstname-empty", "First name cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname-empty", "Last name cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username-empty", "username cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password-empty", "password cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email-empty", "Email cannot be blank!");
	        if (errors.hasErrors()) {
	            return;//Skip the rest of the validation rules
	        }
	        //Additional Validation rules that we defined
	        if (!d.getEmail().getEmailAddress().contains("@")) {//Use Regex to validate email
	            errors.rejectValue("email", "email-invalid", "Email is not valid!");
	        }
	        
	 }
}
