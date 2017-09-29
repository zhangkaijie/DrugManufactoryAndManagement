package com.drugManufactoryAndManagement.spring.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.drugManufactoryAndManagement.spring.dao.PatientDAO;
import com.drugManufactoryAndManagement.spring.dao.PatientDoctorInteractionDAO;
import com.drugManufactoryAndManagement.spring.dao.UserAccountDAO;
import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.UserAccount;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;
import com.drugManufactoryAndManagement.spring.validator.PatientDoctorInteractionValidator;
import com.drugManufactoryAndManagement.spring.validator.PatientValidator;




@Controller
@RequestMapping("/connectDoctor.htm")
public class PatientDoctorInteractionController {

	@Autowired
	@Qualifier("patientDoctorInteractionValidator")
	PatientDoctorInteractionValidator validator;

	@Autowired
	@Qualifier("patientDoctorInteractionDao")
	PatientDoctorInteractionDAO patientDoctorInteractionDao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addInteraction(@ModelAttribute("patientDoctorInteraction") PatientDoctorInteraction pdi, BindingResult result,HttpServletRequest request) throws AdException {
			
		try {			
			HttpSession session = request.getSession();
			Patient p = (Patient)session.getAttribute("patient");
			
			pdi.setPostedBy(p.getUserId());
			System.out.println(pdi.getPostedBy());
			pdi.setPatient(p);
			pdi.setCost(0);
			pdi.setDisease("");
			pdi.setDrug("");
		    pdi.setStatus("processing");
			
			pdi = patientDoctorInteractionDao.create(pdi);
			
			
			return new ModelAndView("addInteractionSuccess", "patientDoctorInteraction", pdi);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}			
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView connectDoctor(HttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView();		
		mv.addObject("patientDoctorInteraction", new PatientDoctorInteraction());
		mv.setViewName("sendRequestToDoctor");
		return mv;
	}

	
}
