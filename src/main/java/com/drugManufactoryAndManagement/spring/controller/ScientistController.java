package com.drugManufactoryAndManagement.spring.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

import com.drugManufactoryAndManagement.spring.dao.DoctorDAO;
import com.drugManufactoryAndManagement.spring.dao.DoctorScientistInteractionDAO;
import com.drugManufactoryAndManagement.spring.dao.PatientDoctorInteractionDAO;
import com.drugManufactoryAndManagement.spring.dao.ScientistDAO;
import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.Doctor;
import com.drugManufactoryAndManagement.spring.pojo.DoctorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;
import com.drugManufactoryAndManagement.spring.pojo.Scientist;
import com.drugManufactoryAndManagement.spring.validator.DoctorValidator;
import com.drugManufactoryAndManagement.spring.validator.ScientistValidator;

@Controller
public class ScientistController {

	@Autowired
	@Qualifier("scientistValidator")
	ScientistValidator validator;

	@Autowired
	@Qualifier("scientistDao")
	ScientistDAO scientistDao;
	
	@Autowired
	@Qualifier("doctorScientistInteractionDao")
	DoctorScientistInteractionDAO doctorScientistInteractionDao;
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value ="/addScientist.htm", method = RequestMethod.POST)
	protected ModelAndView createPatient(@ModelAttribute("scientist") Scientist scientist, BindingResult result,HttpServletRequest request,HttpServletResponse response) throws Exception {
		validator.validate(scientist, result);
		if (result.hasErrors()) {
			System.out.println("add scientist error");
			return new ModelAndView("createScientist","scientist",new Scientist());
		}
		try{
		Scientist s=scientistDao.create(scientist);
		request.getSession().setAttribute("scientist", s);
		return new ModelAndView("scientist","scientist",s);
		}catch(HibernateException e)
		{
			
			return new ModelAndView("error");
		}
	 
	}

	@RequestMapping(value ="/addScientist.htm",method = RequestMethod.GET)
    public String createPatient(@ModelAttribute("scientist")Scientist scientist, BindingResult result) { 
        return "createScientist"; 
    } 
	
	@RequestMapping(value ="/analysis.htm",method = RequestMethod.POST)
    public ModelAndView viewPatient(@ModelAttribute("scientist")Scientist scientist, BindingResult result,HttpServletRequest request)
	    throws AdException{ 
		List<DoctorScientistInteraction> list=new ArrayList<DoctorScientistInteraction>();
       try { 
    	String disease = request.getParameter("disease");
		list =doctorScientistInteractionDao.getListByDisease(disease);
		request.getSession().setAttribute("list", list);
       }catch(HibernateException e)
		{
			
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
       return new ModelAndView("analysis","list",list);
		
    } 

	@RequestMapping(value ="/analysisByDisease.htm",method = RequestMethod.GET)
    public String analysisByDisease(@ModelAttribute("scientist")Scientist scientist, BindingResult result,HttpServletRequest request){
	    		
      return "analysisByDisease";
    } 
	
	@RequestMapping(value ="/analysisHistory.htm",method = RequestMethod.GET)
    public ModelAndView AnalysisHistory(@ModelAttribute("scientist")Scientist scientist, BindingResult result,HttpServletRequest request)
	    throws AdException{ 
		List<DoctorScientistInteraction> list=new ArrayList<DoctorScientistInteraction>();
       try { 
    	Scientist s = (Scientist)request.getSession().getAttribute("scientist");
		list = doctorScientistInteractionDao.getListById(s.getUserId());
		request.getSession().setAttribute("list1", list);
       }catch(HibernateException e)
		{
			
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
       return new ModelAndView("analysisHistory","list1",list);
		
    } 

	
	@RequestMapping(value ="/research.htm",method = RequestMethod.POST)
    public ModelAndView analysis(@ModelAttribute("scientist")Scientist scientist, BindingResult result,HttpServletRequest request)
        throws AdException{	
		try {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", request.getSession().getAttribute("list"));
		mav.setViewName("analysisSuccessful");
		
		String[] c1 = request.getParameterValues("compound1");
		String[] c2 = request.getParameterValues("compound2");
		String[] c3 = request.getParameterValues("compound3");
		String[] disease = request.getParameterValues("disease");
		String[] gene = request.getParameterValues("gene");
		List<DoctorScientistInteraction> list = doctorScientistInteractionDao.getListByDisease(disease[0]);
		for (int i=0;i<list.size();i++) {
			list.get(i).setGene(gene[i]);
			list.get(i).setCompound1(c1[i]);
			list.get(i).setCompound2(c2[i]);
			list.get(i).setCompound3(c3[i]);
			list.get(i).setScientist((Scientist)request.getSession().getAttribute("scientist"));
			list.get(i).setStatus("completed");
			doctorScientistInteractionDao.update(list.get(i));

		}
		System.out.println(c1.length);
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for (int i=0;i<c1.length;i++) {
			if (!map.containsKey(c1[i])) {
		         map.put(c1[i], 1);
			}else {
				Integer n = map.get(c1[i]);
				map.put(c1[i],n+1 );
			}
		}
		for (int i=0;i<c2.length;i++) {
			if (!map.containsKey(c2[i])) {
		         map.put(c2[i], 1);
			}else {
				Integer n = map.get(c2[i]);
				map.put(c2[i],n+1 );
			}
		}
		for (int i=0;i<c3.length;i++) {
			if (!map.containsKey(c3[i])) {
		         map.put(c3[i], 1);
			}else {
				Integer n = map.get(c3[i]);
				map.put(c3[i],n+1 );
			}
		}
		Set<Map.Entry<String, Integer>> set = map.entrySet();
		Iterator<Entry<String,Integer>> i = set.iterator();
		List<String> list1 = new ArrayList<String>();
		List<Integer> list2 = new ArrayList<Integer>();
		while (i.hasNext()) {
			Map.Entry<String, Integer> entry = i.next();
			list1.add(entry.getKey());
			list2.add(entry.getValue());
		}
		mav.addObject("names", list1);
		mav.addObject("values", list2);
		return mav;
	 }catch(Exception e)
		{
			return new ModelAndView("error");
		}     
    } 
	

}
