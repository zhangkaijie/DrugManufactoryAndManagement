package com.drugManufactoryAndManagement.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.drugManufactoryAndManagement.spring.pojo.DrugManufactorScientistInteraction;

public class DrugManufactorScientistInteractionValidator  implements Validator {

	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(DrugManufactorScientistInteraction.class);
	}
	
	 @Override
	    public void validate(Object o, Errors errors) {
	        DrugManufactorScientistInteraction dsi = (DrugManufactorScientistInteraction) o;
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "result", "result-empty", "disease cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "status-empty", "status  cannot be blank!");
	       
	        if (errors.hasErrors()) {
	            return;//Skip the rest of the validation rules
	        }
	        //Additional Validation rules that we defined
	         
	    }
}
