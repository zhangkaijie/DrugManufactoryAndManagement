package com.drugManufactoryAndManagement.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.Email;
import com.drugManufactoryAndManagement.spring.pojo.Scientist;
import com.drugManufactoryAndManagement.spring.pojo.UserAccount;






public class UserAccountDAO extends DAO{

	public UserAccountDAO() {
	}

	public UserAccount get(String username, String password) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from UserAccount where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			UserAccount user = (UserAccount) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get user " + username, e);
		}
	}
	
	public  UserAccount get(int userId) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from UserAccount where userId= :userId");
			q.setInteger("userId", userId);		
			UserAccount user = (UserAccount) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userId, e);
		}
	}
	
	
	public boolean userAccountCorrect(String username)
	{
		boolean isUserNameValid=false;
		try {
			begin();
			System.out.println("inside DAO");
			Transaction tx;
			tx = getSession().beginTransaction();
			try {
				Query q=getSession().createQuery("from UserAccount");
				List<UserAccount> userAccountList=q.list();
				
				for(UserAccount useraccount:userAccountList)
				{
					if(useraccount.getUsername().equals(username))
					{
						return true;
					}
					else
					{
						isUserNameValid=false;
					}
					
				}
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
			
			} finally {
			}
			
		} catch (HibernateException e) {
			rollback();
		
		}
		
		return isUserNameValid;
		
	}

}
