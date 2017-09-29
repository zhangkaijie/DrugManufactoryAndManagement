package com.drugManufactoryAndManagement.spring.dao;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.DrugManufactor;
import com.drugManufactoryAndManagement.spring.pojo.Email;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.Role;
import com.drugManufactoryAndManagement.spring.pojo.Scientist;

public class DrugManufactorDAO extends DAO {

	public DrugManufactor create(DrugManufactor d)
			throws Exception {
		RoleDAO rdao = new RoleDAO();
        Role role = rdao.get(4);
		try {
			begin();
			System.out.println("inside DAO");

			Email email = new Email(d.getEmail().getEmailAddress());
			System.out.println(email.getEmailAddress());
			DrugManufactor drugManufactor = new DrugManufactor(d.getUsername(), d.getPassword());

			drugManufactor.setFirstname(d.getFirstname());
			drugManufactor.setLastname(d.getLastname());
			drugManufactor.setUsername(d.getUsername());
			drugManufactor.setPassword(d.getPassword());
			drugManufactor.setEmail(email);
			drugManufactor.setRole(role);
			email.setUseraccount(drugManufactor);
			getSession().save(drugManufactor);
			commit();
			System.out.println(email.getUseraccount().getUsername());
			return drugManufactor;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

}
