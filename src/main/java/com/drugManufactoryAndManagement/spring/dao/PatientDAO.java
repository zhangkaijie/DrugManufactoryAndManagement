package com.drugManufactoryAndManagement.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.Email;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.Role;
import com.drugManufactoryAndManagement.spring.pojo.UserAccount;




public class PatientDAO extends DAO{

	

	

	public Patient create(Patient p)
			throws Exception {
		RoleDAO rdao = new RoleDAO();
        Role role = rdao.get(1);
		try {
			begin();
			System.out.println("inside DAO");

			Email email = new Email(p.getEmail().getEmailAddress());
			System.out.println(email.getEmailAddress());
			Patient patient = new Patient(p.getUsername(), p.getPassword());
			patient.setFirstname(p.getFirstname());
			patient.setLastname(p.getLastname());
			patient.setUsername(p.getUsername());
			patient.setPassword(p.getPassword());
			patient.setEmail(email);
			patient.setRole(role);
			email.setUseraccount(patient);
			getSession().save(patient);
			commit();
			System.out.println(email.getUseraccount().getUsername());
			return patient;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	

	
}
