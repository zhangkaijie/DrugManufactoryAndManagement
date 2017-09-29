package com.drugManufactoryAndManagement.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drugManufactoryAndManagement.spring.dao.CompoundManufactorDAO;
import com.drugManufactoryAndManagement.spring.dao.PatientDAO;
import com.drugManufactoryAndManagement.spring.dao.UserAccountDAO;
import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.CompoundManufactor;
import com.drugManufactoryAndManagement.spring.pojo.Doctor;
import com.drugManufactoryAndManagement.spring.pojo.DrugManufactor;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.Role;
import com.drugManufactoryAndManagement.spring.pojo.Scientist;
import com.drugManufactoryAndManagement.spring.pojo.UserAccount;




@Controller
public class UserAccountController {
 
	@Autowired
	@Qualifier("userAccountDao")
	UserAccountDAO userAccountDao;
	
	
	@RequestMapping(value ="/login.htm", method = RequestMethod.POST)
	public ModelAndView CheckUserAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Hii");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if(username!=null)
		{
		   session.setAttribute("password", password);
		   session.setAttribute("username", username);
		}
		if(username==null)
		{
			username=(String)session.getAttribute("username");
			password=(String)session.getAttribute("password");
		}
	 
		if (username != null&&password!=null) {
			System.out.println(username);
			System.out.println(password);
			UserAccount useraccount = new UserAccount();
			try{
			useraccount = userAccountDao.get(username, password);
			System.out.println(useraccount.getUserId());
			}
			catch(Exception e){
				ModelAndView mv = new ModelAndView("error");
				return mv;
			}
		
			Role role =  useraccount.getRole();
			if (role.getRoleid() == 1) {
				System.out.println("patient");
				Patient p = (Patient)useraccount;
				session.setAttribute("patient", p);
				return new ModelAndView("patient");

			}
			
			if (role.getRoleid() == 2) {
				System.out.println("doctor");
				Doctor d = (Doctor)useraccount;
				session.setAttribute("doctor", d);
				return new ModelAndView("doctor");

			}
             
			if (role.getRoleid() == 3) {
				System.out.println("scientist");
				Scientist s = (Scientist)useraccount;
				session.setAttribute("scientist", s);
				return new ModelAndView("scientist");

			}

			if (role.getRoleid() == 4) {
				System.out.println("drugManufactor");
				DrugManufactor d = (DrugManufactor)useraccount;
				session.setAttribute("drugManufactor", d);
				return new ModelAndView("drugManufactor");

			}

			if (role.getRoleid() == 5) {
				System.out.println("compoundManufactor");
				CompoundManufactor c = (CompoundManufactor)useraccount;
				session.setAttribute("compoundManufactor", c);
				return new ModelAndView("compoundManufactor");

			}

			
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		return mv;

	}
	
	@RequestMapping(value ="/login.htm", method = RequestMethod.GET) 
	public String GoToLoginPage (){
		return "login";
	}
		
	
	
	// Login Check Redirect To
		// For AJAX
		@RequestMapping("/LoginCheck.htm")
		public String SelectGlobalServices(@RequestBody String username, HttpServletRequest request,
				HttpServletResponse response) {
			System.out.println(username);
			UserAccountDAO udao = new UserAccountDAO();
			boolean isCorrectUserAccount = udao.userAccountCorrect(username);
			System.out.println(isCorrectUserAccount);
			JSONObject json = new JSONObject();
			json.put("isCorrectUserAccount", isCorrectUserAccount);
			json.keys();
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		// register Check Redirect To
				// For AJAX
				@RequestMapping("/RegisterCheck.htm")
				public String checkRegister(@RequestBody String username, HttpServletRequest request,
						HttpServletResponse response) {
					System.out.println(username);
					UserAccountDAO udao = new UserAccountDAO();
					
					boolean isCorrectUserAccount = false;
					if (udao.userAccountCorrect(username)) {
						isCorrectUserAccount = false;
					}else {
						isCorrectUserAccount = true;
					}
					JSONObject json = new JSONObject();
					json.put("isCorrectUserAccount", isCorrectUserAccount);
					json.keys();
					PrintWriter out;
					try {
						out = response.getWriter();
						out.println(json);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
}

