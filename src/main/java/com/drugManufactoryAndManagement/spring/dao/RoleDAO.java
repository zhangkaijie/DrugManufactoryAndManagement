package com.drugManufactoryAndManagement.spring.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.Role;
import com.drugManufactoryAndManagement.spring.pojo.UserAccount;

public class RoleDAO extends DAO{

	public Role get(int roleId) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Role where roleId= :roleId");
			q.setInteger("roleId", roleId);		
			Role role =  (Role)q.uniqueResult();
			commit();
			return role;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get user " +  e);
		}
	}
}
