package com.drugManufactoryAndManagement.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import com.drugManufactoryAndManagement.spring.dao.CompoundDrugInteractionDAO;
import com.drugManufactoryAndManagement.spring.dao.DoctorDAO;
import com.drugManufactoryAndManagement.spring.dao.PatientDAO;
import com.drugManufactoryAndManagement.spring.dao.PatientDoctorInteractionDAO;
import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.Doctor;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;
import com.drugManufactoryAndManagement.spring.validator.DoctorValidator;
import com.drugManufactoryAndManagement.spring.validator.PatientValidator;

@Controller
public class DoctorController {

	@Autowired
	@Qualifier("doctorValidator")
	DoctorValidator validator;

	@Autowired
	@Qualifier("doctorDao")
	DoctorDAO doctortDao;

	@Autowired
	@Qualifier("patientDoctorInteractionDao")
	PatientDoctorInteractionDAO patientDoctorInteractionDao;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value ="/addDoctor.htm", method = RequestMethod.POST)
	protected ModelAndView createPatient(@ModelAttribute("doctor") Doctor doctor, BindingResult result,HttpServletRequest request,HttpServletResponse response) throws Exception {
		validator.validate(doctor, result);
		if (result.hasErrors()) {
			System.out.println("add doctor error");
			return new ModelAndView("createDoctor","doctor",new Doctor());
		}	
		try{
	    Doctor d=doctortDao.create(doctor);
		request.getSession().setAttribute("doctor", d);
		return new ModelAndView("doctor","doctor",d);
		}catch(HibernateException e)
		{
			
			return new ModelAndView("error");
		}
	 
	}

	@RequestMapping(value ="/addDoctor.htm",method = RequestMethod.GET)
    public String createPatient(@ModelAttribute("doctor")Doctor doctor, BindingResult result) { 
        return "createDoctor"; 
    } 
	
	@RequestMapping(value ="/viewPatient.htm",method = RequestMethod.GET)
    public ModelAndView viewPatient(@ModelAttribute("doctor")Doctor doctor, BindingResult result,HttpServletRequest request)
	    throws AdException{ 
		List<PatientDoctorInteraction> list=new ArrayList<PatientDoctorInteraction>();
       try { 

		list =patientDoctorInteractionDao.getListByStatus("processing");
		request.getSession().setAttribute("list", list);
       }catch(HibernateException e)
		{
			
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
       return new ModelAndView("responsePatient","list",list);
		
    } 
	
	@RequestMapping(value ="/responsePatient.htm",method = RequestMethod.POST)
    public String responsePatient(@ModelAttribute("doctor")Doctor doctor, BindingResult result,HttpServletRequest request)
	    throws Exception{ 
		try {
		 Doctor d = (Doctor)request.getSession().getAttribute("doctor");
		
		 List<PatientDoctorInteraction> list =(List<PatientDoctorInteraction>)request.getSession().getAttribute("list");
		 String description[] =request.getParameterValues("description") ;	
		 String disease[] =request.getParameterValues("disease");	
		 String drug[] = request.getParameterValues("drug");
		 String cost[] = request.getParameterValues("cost");
		 
		 for (int i=0;i<list.size();i++) {
			 PatientDoctorInteraction pdi = patientDoctorInteractionDao.getById(list.get(i).getPatientDoctorId());
			 pdi.setDisease(description[i]);
			 pdi.setStatus("completed");
			 pdi.setDisease(disease[i]);
			 pdi.setDrug(drug[i]);
			 pdi.setCost(Float.parseFloat(cost[i]));
			 pdi.setDoctor(d);
			
			 patientDoctorInteractionDao.update(pdi);
		 }
		 
	    }catch(HibernateException e)
			{
				
				throw new AdException("Exception while creating user: " + e.getMessage());
			}
	     return "respondPatientSuccessful";
	 		
    } 
	
	@RequestMapping(value ="/patientRequestHistory.htm",method = RequestMethod.GET)
    public ModelAndView viewPatientRequestHistory(@ModelAttribute("doctor")Doctor doctor, BindingResult result,HttpServletRequest request)
	    throws AdException{ 
		List<PatientDoctorInteraction> list=new ArrayList<PatientDoctorInteraction>();
       try { 
		//PatientDoctorInteractionDAO pdid = new PatientDoctorInteractionDAO();
		System.out.println("--"+doctor.getUsername());
		Doctor d =(Doctor)request.getSession().getAttribute("doctor");
		list =patientDoctorInteractionDao.getListByDoctorId(d.getUserId());
		System.out.println(list.size());
		request.getSession().setAttribute("list1", list);
       }catch(HibernateException e)
		{
			
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
       return new ModelAndView("patientRequestHistory","list1",list);
		
    } 
}
