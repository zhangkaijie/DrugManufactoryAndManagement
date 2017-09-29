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

import com.drugManufactoryAndManagement.spring.dao.DoctorScientistInteractionDAO;
import com.drugManufactoryAndManagement.spring.dao.DrugManufactorDAO;
import com.drugManufactoryAndManagement.spring.dao.DrugManufactorScientistInteractionDAO;
import com.drugManufactoryAndManagement.spring.dao.PatientDAO;
import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.DoctorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.DrugManufactor;
import com.drugManufactoryAndManagement.spring.pojo.DrugManufactorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.Scientist;
import com.drugManufactoryAndManagement.spring.validator.DrugManufactorValidator;
import com.drugManufactoryAndManagement.spring.validator.PatientValidator;

@Controller
public class DrugManufactorController {

	@Autowired
	@Qualifier("drugManufactorValidator")
	DrugManufactorValidator validator;

	@Autowired
	@Qualifier("drugManufactorDao")
	DrugManufactorDAO drugManufactorDao;
	
	@Autowired
	@Qualifier("drugManufactorScientistInteractionDao")
	DrugManufactorScientistInteractionDAO drugManufactorScientistInteractionDao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value ="/addDrugManufactor.htm", method = RequestMethod.POST)
	protected ModelAndView createDrugManufactor(@ModelAttribute("drugManufactor") DrugManufactor drugManufactor, BindingResult result,HttpServletRequest request,HttpServletResponse response) throws Exception {
		validator.validate(drugManufactor, result);
		if (result.hasErrors()) {
			System.out.println("add drugManufactor error");
			return new ModelAndView("createDrugManufactor","drugManufactor",new DrugManufactor());
		}
		try{
		DrugManufactor d = drugManufactorDao.create(drugManufactor);
		request.getSession().setAttribute("drugManufactor", d);
		return new ModelAndView("drugManufactor","drugManufactor",d);
		}catch(HibernateException e)
		{
			
			return new ModelAndView("error");
		}
	 
	}

	@RequestMapping(value ="/addDrugManufactor.htm",method = RequestMethod.GET)
    public String createDrugManufactor(@ModelAttribute("drugManufactor")DrugManufactor drugManufactor, BindingResult result) { 
        return "createDrugManufactor"; 
    } 
	
	@RequestMapping(value ="/viewResearchResult.htm",method = RequestMethod.GET)
    public ModelAndView viewPatient(@ModelAttribute("drugManufactor")DrugManufactor drugManufactor, BindingResult result,HttpServletRequest request)
	    throws AdException{ 
		List<DrugManufactorScientistInteraction> list=new ArrayList<DrugManufactorScientistInteraction>();
       try { 	
		list = drugManufactorScientistInteractionDao.getListByStatus("processing");
		request.getSession().setAttribute("list1", list);
       }catch(HibernateException e)
		{
			
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
       return new ModelAndView("researchResult","list1",list);
		
    } 

	@RequestMapping(value ="/changeStatus.htm",method = RequestMethod.POST)
	 public ModelAndView changeStatus(@ModelAttribute("drugManufactor")DrugManufactor drugManufactor, BindingResult result,HttpServletRequest request)
			    throws Exception{ 
		
		List<DrugManufactorScientistInteraction> list = (List<DrugManufactorScientistInteraction>)request.getSession().getAttribute("list1");
		DrugManufactor d = (DrugManufactor)request.getSession().getAttribute("drugManufactor");
		System.out.println(d.getUsername());
		for (int i=0;i<list.size();i++) {
			list.get(i).setStatus("completed");
			list.get(i).setDrugManufactor((DrugManufactor)request.getSession().getAttribute("drugManufactor"));
			drugManufactorScientistInteractionDao.update(list.get(i));
		}
		return new ModelAndView("drugManufactor");
	}
	
	@RequestMapping(value ="/researchResultHistory.htm",method = RequestMethod.GET)
    public ModelAndView viewHistory(@ModelAttribute("drugManufactor")DrugManufactor drugManufactor, BindingResult result,HttpServletRequest request)
	    throws AdException{ 
		List<DrugManufactorScientistInteraction> list=new ArrayList<DrugManufactorScientistInteraction>();
       try { 
    	    
		
		list = drugManufactorScientistInteractionDao.getList();
		request.getSession().setAttribute("list2", list);
       }catch(HibernateException e)
		{
			
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
       return new ModelAndView("researchResultHistory","list2",list);
		
    } 

}
