package com.drugManufactoryAndManagement.spring.dao;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.CompoundManufactor;
import com.drugManufactoryAndManagement.spring.pojo.DrugManufactor;
import com.drugManufactoryAndManagement.spring.pojo.Email;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.Role;

public class CompoundManufactorDAO extends DAO {

	public CompoundManufactor create(CompoundManufactor c)
			throws Exception {
		RoleDAO rdao = new RoleDAO();
        Role role = rdao.get(5);
		try {
			begin();
			System.out.println("inside DAO");

			Email email = new Email(c.getEmail().getEmailAddress());
			System.out.println(email.getEmailAddress());
			CompoundManufactor compoundManufactor = new CompoundManufactor(c.getUsername(), c.getPassword());

			compoundManufactor.setFirstname(c.getFirstname());
			compoundManufactor.setLastname(c.getLastname());
			compoundManufactor.setUsername(c.getUsername());
			compoundManufactor.setPassword(c.getPassword());
			compoundManufactor.setEmail(email);
			compoundManufactor.setRole(role);
			email.setUseraccount(compoundManufactor);
			getSession().save(compoundManufactor);
			commit();
			System.out.println(email.getUseraccount().getUsername());
			return compoundManufactor;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
}
