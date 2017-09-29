package com.drugManufactoryAndManagement.spring.dao;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.Doctor;
import com.drugManufactoryAndManagement.spring.pojo.Email;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.Role;
import com.drugManufactoryAndManagement.spring.pojo.Scientist;

public class ScientistDAO extends DAO{

	public Scientist create(Scientist s)
			throws Exception {
		RoleDAO rdao = new RoleDAO();
        Role role = rdao.get(3);
		try {
			begin();
			System.out.println("inside DAO");

			Email email = new Email(s.getEmail().getEmailAddress());
			System.out.println(email.getEmailAddress());
			Scientist scientist = new Scientist(s.getUsername(), s.getPassword());

			scientist.setFirstname(s.getFirstname());
			scientist.setLastname(s.getLastname());
			scientist.setUsername(s.getUsername());
			scientist.setPassword(s.getPassword());
			scientist.setEmail(email);
			scientist.setRole(role);
			email.setUseraccount(scientist);
			getSession().save(scientist);
			commit();
			System.out.println(email.getUseraccount().getUsername());
			return scientist;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
}
