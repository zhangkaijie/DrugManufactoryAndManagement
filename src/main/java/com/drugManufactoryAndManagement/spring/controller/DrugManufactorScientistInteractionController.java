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

import com.drugManufactoryAndManagement.spring.dao.DoctorScientistInteractionDAO;
import com.drugManufactoryAndManagement.spring.dao.DrugManufactorScientistInteractionDAO;
import com.drugManufactoryAndManagement.spring.dao.UserAccountDAO;
import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.Doctor;
import com.drugManufactoryAndManagement.spring.pojo.DoctorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.DrugManufactorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.Scientist;
import com.drugManufactoryAndManagement.spring.validator.DoctorScientistInteractionValidator;
import com.drugManufactoryAndManagement.spring.validator.DrugManufactorScientistInteractionValidator;

@Controller
@RequestMapping("/connectDrugManufactor.htm")
public class DrugManufactorScientistInteractionController {

	@Autowired
	@Qualifier("drugManufactorScientistInteractionValidator")
	DrugManufactorScientistInteractionValidator validator;

	@Autowired
	@Qualifier("drugManufactorScientistInteractionDao")
	DrugManufactorScientistInteractionDAO drugManufactorScientistInteractionDao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView connectDrugManufactor(HttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView();		
		mv.addObject("drugManufactorScientistInteraction", new DrugManufactorScientistInteraction());
		mv.setViewName("sendResultToDrugManufactor");
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addInteractionWithDrugManufactor(@ModelAttribute("drugManufactorScientistInteraction") DrugManufactorScientistInteraction dsi, BindingResult result,HttpServletRequest request) throws AdException {
			
		try {			
			HttpSession session = request.getSession();
			Scientist s = (Scientist)session.getAttribute("scientist");
			dsi.setPostedBy(s.getUserId());
			System.out.println(dsi.getPostedBy());
			String result1 = request.getParameter("result");
			dsi.setResult(result1);
			dsi.setScientist(s);
			dsi.setStatus("processing");
			dsi = drugManufactorScientistInteractionDao.create(dsi);
			return new ModelAndView("sendResultSuccess", "drugManufactorScientistInteraction", dsi);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}			
	}


}
