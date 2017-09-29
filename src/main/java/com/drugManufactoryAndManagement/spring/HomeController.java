package com.drugManufactoryAndManagement.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drugManufactoryAndManagement.spring.pojo.CompoundManufactor;
import com.drugManufactoryAndManagement.spring.pojo.Doctor;
import com.drugManufactoryAndManagement.spring.pojo.DrugManufactor;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.Scientist;
import com.drugManufactoryAndManagement.spring.pojo.UserAccount;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	protected String home() throws Exception {
		System.out.print("home page");

		return "home";

	}
	
	@RequestMapping(value = "/signin.htm", method = RequestMethod.GET)
	protected ModelAndView signin() throws Exception {
		System.out.print("registerDoctor");

		return new ModelAndView("login", "useraccount", new UserAccount());

	}
	
		
	@RequestMapping(value = "/introduction", method = RequestMethod.GET)
	public String introduction() {
		return "introduction";
	}
	
	
	
	@RequestMapping(value = "/createPatient.htm", method = RequestMethod.GET)
	protected ModelAndView registerPatient() throws Exception {
		System.out.print("registerPatient");

		return new ModelAndView("createPatient", "patient", new Patient());

	}
	
	@RequestMapping(value = "/createDoctor.htm", method = RequestMethod.GET)
	protected ModelAndView registerDoctor() throws Exception {
		System.out.print("registerDoctor");

		return new ModelAndView("createDoctor", "doctor", new Doctor());

	}
	
	@RequestMapping(value = "/createScientist.htm", method = RequestMethod.GET)
	protected ModelAndView registerScientist() throws Exception {
		System.out.print("registerScientist");

		return new ModelAndView("createScientist", "scientist", new Scientist());

	}
	
	@RequestMapping(value = "/createDrugManufactor.htm", method = RequestMethod.GET)
	protected ModelAndView registerDrugManufactor() throws Exception {
		System.out.print("registerDrugManufactor");

		return new ModelAndView("createDrugManufactor", "drugManufactor", new DrugManufactor());

	}

	@RequestMapping(value = "/createCompoundManufactor.htm", method = RequestMethod.GET)
	protected ModelAndView registerCompoundManufactor() throws Exception {
		System.out.print("registerCompoundManufactor");

		return new ModelAndView("createCompoundManufactor", "compoundManufactor", new CompoundManufactor());

	}
	
	@RequestMapping(value = "/PatientHome.htm", method = RequestMethod.GET)
	public String PatientHome() {
		return "patient";
	}
	
	@RequestMapping(value = "/DoctorHome.htm", method = RequestMethod.GET)
	public String DoctorHome() {
		return "doctor";
	}
	
	@RequestMapping(value = "/ScientistHome.htm", method = RequestMethod.GET)
	public String ScientistHome() {
		return "scientist";
	}

	@RequestMapping(value = "/DrugManufactorHome.htm", method = RequestMethod.GET)
	public String DrugManufactortHome() {
		return "drugManufactor";
	}

	@RequestMapping(value = "/CompoundManufactorHome.htm", method = RequestMethod.GET)
	public String CompoundManufactortHome() {
		return "compoundManufactor";
	}
	
	@RequestMapping("/logout")
	public ModelAndView Logout(HttpServletRequest request,
			HttpServletResponse response)
	{
		request.getSession().invalidate();
		request.getSession(false);
		
		ModelAndView mv=new ModelAndView("home");
		return mv;
		
	}


}
