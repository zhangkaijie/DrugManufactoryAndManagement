package com.drugManufactoryAndManagement.spring.controller;

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

import com.drugManufactoryAndManagement.spring.dao.CompoundDrugInteractionDAO;
import com.drugManufactoryAndManagement.spring.dao.DoctorScientistInteractionDAO;
import com.drugManufactoryAndManagement.spring.dao.PatientDoctorInteractionDAO;
import com.drugManufactoryAndManagement.spring.dao.UserAccountDAO;
import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.Doctor;
import com.drugManufactoryAndManagement.spring.pojo.DoctorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;
import com.drugManufactoryAndManagement.spring.validator.DoctorScientistInteractionValidator;
import com.drugManufactoryAndManagement.spring.validator.PatientDoctorInteractionValidator;

@Controller
@RequestMapping("/connectScientist.htm")
public class DoctorScientistInteractionController {

	@Autowired
	@Qualifier("doctorScientistInteractionValidator")
	DoctorScientistInteractionValidator validator;

	@Autowired
	@Qualifier("doctorScientistInteractionDao")
	DoctorScientistInteractionDAO doctorScientistInteractionDao;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView connectScientist(HttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView();		
		mv.addObject("doctorScientistInteraction", new DoctorScientistInteraction());
		mv.setViewName("sendDataToScientist");
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addInteraction(@ModelAttribute("doctorScientistInteraction") DoctorScientistInteraction dsi, BindingResult result,HttpServletRequest request) throws AdException {
			
		try {			
			
			HttpSession session = request.getSession();
			Doctor d = (Doctor)session.getAttribute("doctor");
			dsi.setPostedBy(d.getUserId());
			dsi.setDoctor(d);
			dsi.setCompound1("");
			dsi.setCompound2("");
			dsi.setCompound3("");
			dsi.setGene("");
		    dsi.setStatus("processing");
			dsi = doctorScientistInteractionDao.create(dsi);
			return new ModelAndView("sendDataSuccess", "doctorScientistInteraction", dsi);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}			
	}
}
