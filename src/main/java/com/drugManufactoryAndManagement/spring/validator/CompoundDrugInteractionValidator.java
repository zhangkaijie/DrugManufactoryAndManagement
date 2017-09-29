package com.drugManufactoryAndManagement.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.drugManufactoryAndManagement.spring.pojo.CompoundDrugInteraction;
import com.drugManufactoryAndManagement.spring.pojo.DoctorScientistInteraction;

public class CompoundDrugInteractionValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(CompoundDrugInteraction.class);
	}
	
	 @Override
	    public void validate(Object o, Errors errors) {
	        CompoundDrugInteraction cdi = (CompoundDrugInteraction) o;
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "compound", "compound-empty", "compound cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "message-empty", "message cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "status-empty", "status cannot be blank!");
	       
	        if (errors.hasErrors()) {
	            return;//Skip the rest of the validation rules
	        }
	        //Additional Validation rules that we defined
	         
	    }
}
