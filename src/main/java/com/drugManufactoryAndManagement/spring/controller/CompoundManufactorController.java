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
import com.drugManufactoryAndManagement.spring.dao.CompoundManufactorDAO;
import com.drugManufactoryAndManagement.spring.dao.DoctorDAO;
import com.drugManufactoryAndManagement.spring.dao.PatientDoctorInteractionDAO;
import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.CompoundDrugInteraction;
import com.drugManufactoryAndManagement.spring.pojo.CompoundManufactor;
import com.drugManufactoryAndManagement.spring.pojo.Doctor;
import com.drugManufactoryAndManagement.spring.pojo.DrugManufactor;
import com.drugManufactoryAndManagement.spring.pojo.DrugManufactorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;
import com.drugManufactoryAndManagement.spring.validator.CompoundManufactorValidator;
import com.drugManufactoryAndManagement.spring.validator.DoctorValidator;

@Controller
public class CompoundManufactorController {

	@Autowired
	@Qualifier("compoundManufactorValidator")
	CompoundManufactorValidator validator;

	@Autowired
	@Qualifier("compoundManufactorDao")
	CompoundManufactorDAO compoundManufactorDao;
	
	@Autowired
	@Qualifier("compoundDrugInteractionDao")
	CompoundDrugInteractionDAO compoundDrugInteractionDao;

	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value ="/addCompoundManufactor.htm", method = RequestMethod.POST)
	protected ModelAndView createPatient(@ModelAttribute("compoundManufactor") CompoundManufactor compoundManufactor, BindingResult result,HttpServletRequest request,HttpServletResponse response) throws Exception {
		validator.validate(compoundManufactor, result);
		if (result.hasErrors()) {
			System.out.println("add compoundManufactor error");
			return new ModelAndView("createCompoundManufactor","compoundManufactor",new CompoundManufactor());
		}
		try{
		CompoundManufactor c = compoundManufactorDao.create(compoundManufactor);
		request.getSession().setAttribute("compoundManufactor", c);
		return new ModelAndView("compoundManufactor","compoundManufactor",c);
		}catch(HibernateException e)
		{
			
			return new ModelAndView("error");
		}
	 
	}

	@RequestMapping(value ="/addCompoundManufactor.htm",method = RequestMethod.GET)
    public String createPatient(@ModelAttribute("compoundManufactor")CompoundManufactor compoundManufactor, BindingResult result) { 
        return "createCompoundManufactor"; 
    } 
	
	@RequestMapping(value ="/message.htm",method = RequestMethod.GET)
    public ModelAndView viewPatient(@ModelAttribute("compoundManufactor")CompoundManufactor compoundManufactor, BindingResult result,HttpServletRequest request)
	    throws AdException{ 
		List<CompoundDrugInteraction> list=new ArrayList<CompoundDrugInteraction>();
       try { 
		
		list =compoundDrugInteractionDao.getListByStatus("processing");
		request.getSession().setAttribute("list2", list);
       }catch(HibernateException e)
		{
			
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
       return new ModelAndView("responseDrugManufactor","list2",list);
		
    } 


	@RequestMapping(value ="/changeStatus2.htm",method = RequestMethod.POST)
	 public ModelAndView changeStatus(@ModelAttribute("compoundManufactor")CompoundManufactor drugManufactor, BindingResult result,HttpServletRequest request)
			    throws Exception{ 
		
		List<CompoundDrugInteraction> list = (List<CompoundDrugInteraction>)request.getSession().getAttribute("list2");
		
		CompoundManufactor c = (CompoundManufactor)request.getSession().getAttribute("compoundManufactor");
		System.out.println(c.getUsername());
		for (int i=0;i<list.size();i++) {
			list.get(i).setStatus("completed");
			list.get(i).setCompoundManufactor((CompoundManufactor)request.getSession().getAttribute("compoundManufactor"));
			compoundDrugInteractionDao.update(list.get(i));
		}
		return new ModelAndView("compoundManufactor");
	}
	
	@RequestMapping(value ="/messageHistory.htm",method = RequestMethod.GET)
    public ModelAndView viewHistory(@ModelAttribute("compoundManufactor")CompoundManufactor compoundManufactor, BindingResult result,HttpServletRequest request)
	    throws AdException{ 
		List<CompoundDrugInteraction> list=new ArrayList<CompoundDrugInteraction>();
       try { 
		list = compoundDrugInteractionDao.getList();
		request.getSession().setAttribute("list3", list);
       }catch(HibernateException e)
		{
			
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
       return new ModelAndView("messageHistory","list3",list);
		
    } 

	

}
