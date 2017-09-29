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
import com.drugManufactoryAndManagement.spring.dao.UserAccountDAO;
import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.CompoundDrugInteraction;
import com.drugManufactoryAndManagement.spring.pojo.Doctor;
import com.drugManufactoryAndManagement.spring.pojo.DoctorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.DrugManufactor;
import com.drugManufactoryAndManagement.spring.validator.CompoundDrugInteractionValidator;
import com.drugManufactoryAndManagement.spring.validator.DoctorScientistInteractionValidator;


@Controller
@RequestMapping("/connectCompoundManufactor.htm")
public class CompoundDrugInteractionController {

	@Autowired
	@Qualifier("compoundDrugInteractionValidator")
	CompoundDrugInteractionValidator validator;
	
	@Autowired
	@Qualifier("compoundDrugInteractionDao")
	CompoundDrugInteractionDAO compoundDrugInteractionDao;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView connectCompoundManufactor(HttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView();		
		mv.addObject("compoundDrugInteraction", new CompoundDrugInteraction());
		mv.setViewName("sendDataToCompoundManufactor");
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addInteraction(@ModelAttribute("compoundDrugInteraction") CompoundDrugInteraction cdi, BindingResult result,HttpServletRequest request) throws AdException {
			
		try {			
			
			HttpSession session = request.getSession();
			DrugManufactor d = (DrugManufactor)session.getAttribute("drugManufactor");
			cdi.setPostedBy(d.getUserId());
			String compound = request.getParameter("compound");
			String message = request.getParameter("message");
			cdi.setCompound(compound);
			
			cdi.setDrugManufactor(d);
			cdi.setMessage(message);
	
			cdi.setStatus("processing");
			
			cdi = compoundDrugInteractionDao.create(cdi);
			
			
			return new ModelAndView("sendDataSuccessToCompound", "compoundDrugInteraction", cdi);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}			
	}
}
