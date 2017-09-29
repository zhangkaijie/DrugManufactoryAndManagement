package com.drugManufactoryAndManagement.spring.dao;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.Doctor;
import com.drugManufactoryAndManagement.spring.pojo.Email;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.Role;

public class DoctorDAO extends DAO{
	

	public Doctor create(Doctor d)
			throws Exception {
		RoleDAO rdao = new RoleDAO();
        Role role = rdao.get(2);
		try {
			begin();
			System.out.println("inside DAO");

			Email email = new Email(d.getEmail().getEmailAddress());
			System.out.println(email.getEmailAddress());
			Doctor doctor = new Doctor(d.getUsername(), d.getPassword());

			doctor.setFirstname(d.getFirstname());
			doctor.setLastname(d.getLastname());
			doctor.setUsername(d.getUsername());
			doctor.setPassword(d.getPassword());
			doctor.setEmail(email);
			doctor.setRole(role);
			email.setUseraccount(doctor);
			getSession().save(doctor);
			commit();
			System.out.println(email.getUseraccount().getUsername());
			return doctor;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	
}
