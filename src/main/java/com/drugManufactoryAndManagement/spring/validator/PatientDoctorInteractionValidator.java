package com.drugManufactoryAndManagement.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;

public class PatientDoctorInteractionValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(PatientDoctorInteraction.class);
	}
	
	 @Override
	    public void validate(Object o, Errors errors) {
	        PatientDoctorInteraction pdi = (PatientDoctorInteraction) o;
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "diseaseDescription", "diseaseDescription-empty", "Description cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "status-empty", "status name cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "drug", "drug-empty", "drug cannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "disease", "disease-empty", "diseasecannot be blank!");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "cost-empty", "cost cannot be blank!");
	        if (errors.hasErrors()) {
	            return;//Skip the rest of the validation rules
	        }
	        //Additional Validation rules that we defined
	        if (pdi.getCost()<0) {//Use Regex to validate email
	            errors.rejectValue("cost", "cost-invalid", "Cost is not valid!");
	        }
	    }
}
