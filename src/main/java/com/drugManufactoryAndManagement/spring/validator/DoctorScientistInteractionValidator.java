package com.drugManufactoryAndManagement.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.drugManufactoryAndManagement.spring.pojo.DoctorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;

public class DoctorScientistInteractionValidator implements Validator{

	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(DoctorScientistInteraction.class);
	}
	
	 @Override
	    public void validate(Object o, Errors errors) {
	        DoctorScientistInteraction dsi = (DoctorScientistInteraction) o;
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "disease", "disease-empty", "disease cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "status-empty", "status  cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "drug", "drug-empty", "drug cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gene", "gene-empty", "gene cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "compound1", "compound1-empty", "compound1 cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "compound2", "compound2-empty", "compound2 cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "compound3", "compound3-empty", "compound3 cannot be blank!");
	        if (errors.hasErrors()) {
	            return;//Skip the rest of the validation rules
	        }
	        //Additional Validation rules that we defined
	         
	    }

}
