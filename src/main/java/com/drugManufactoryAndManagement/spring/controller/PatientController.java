package com.drugManufactoryAndManagement.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.dao.PatientDAO;
import com.drugManufactoryAndManagement.spring.dao.PatientDoctorInteractionDAO;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;
import com.drugManufactoryAndManagement.spring.validator.PatientValidator;




@Controller
public class PatientController {
	@Autowired
	@Qualifier("patientValidator")
	PatientValidator validator;

	@Autowired
	@Qualifier("patientDao")
	PatientDAO patientDao;
	
	@Autowired
	@Qualifier("patientDoctorInteractionDao")
	PatientDoctorInteractionDAO patientDoctorInteractionDao;
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value ="/addPatient.htm", method = RequestMethod.POST)
	protected ModelAndView createPatient(@ModelAttribute("patient") Patient patient, BindingResult result,HttpServletRequest request,HttpServletResponse response) throws Exception {
		validator.validate(patient, result);
		if (result.hasErrors()) {
			System.out.println("add patient error");
			return new ModelAndView("createPatient","patient",new Patient());
		}
		
		try{
		Patient p=patientDao.create(patient);
		request.getSession().setAttribute("patient", p);
		return new ModelAndView("patient","patient",p);
		}catch(HibernateException e)
		{
			
			return new ModelAndView("error");
		}
	 
	}

	@RequestMapping(value ="/addPatient.htm",method = RequestMethod.GET)
    public String createPatient(@ModelAttribute("patient")Patient patient, BindingResult result) { 
        return "createPatient"; 
    } 
	
	@RequestMapping(value = "/viewHistory.htm", method = RequestMethod.GET)
	public ModelAndView getList(HttpServletRequest request) 
	 throws AdException{
		try {
		   HttpSession session = request.getSession();
		   System.out.println("---"+session.getAttribute("patient"));
		   Patient patient =(Patient)session.getAttribute("patient");
		   List<PatientDoctorInteraction> list=patientDoctorInteractionDao.getListById(patient.getUserId());
		   session.setAttribute("patient", patient);
		   return new ModelAndView("healthRecords","list",list);
		   
		}catch(HibernateException e)
		{
			
			throw new AdException("Exception while creating user: " + e.getMessage());
		} 
	}
	
	
	
	
	@RequestMapping(value ="/viewBilling.htm", method = RequestMethod.GET)
	public ModelAndView viewBilling (HttpServletRequest request) 
	       throws AdException{
		try {
			   HttpSession session = request.getSession();
			   Patient patient = (Patient)session.getAttribute("patient");
			   List<PatientDoctorInteraction> list=patientDoctorInteractionDao.getListById(patient.getUserId());
			   float cost=0;
			   if (list == null) {
				   System.out.println("list null");
				   return new ModelAndView("billing","cost",cost);
			   }else {
				   System.out.println("list no null");
			   
			        for (PatientDoctorInteraction pdi : list) {
				      cost=cost+pdi.getCost();
			       }
			   }
			   return new ModelAndView("billing","cost",cost);
			}catch(HibernateException e)
			{
				
				throw new AdException("Exception while creating user: " + e.getMessage());
			} 

		   
	}

	
}
