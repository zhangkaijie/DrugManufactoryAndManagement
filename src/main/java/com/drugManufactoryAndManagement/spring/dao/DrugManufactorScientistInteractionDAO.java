package com.drugManufactoryAndManagement.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.DoctorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.DrugManufactorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;

public class DrugManufactorScientistInteractionDAO extends DAO {

	public DrugManufactorScientistInteraction create(DrugManufactorScientistInteraction drugManufactorScientistInteraction) 
			   throws AdException{
				 try {
			            begin();            
			            getSession().save(drugManufactorScientistInteraction);     
			            commit();
			            return drugManufactorScientistInteraction;
			        } catch (HibernateException e) {
			            rollback();
			            throw new AdException("Exception while creating advert: " + e.getMessage());
			        }
			 }
	
	 public List<DrugManufactorScientistInteraction> getList()
	         throws AdException{
			try {
				begin();

				Transaction tx;
				tx = getSession().beginTransaction();
				try {
					Query query = getSession().createQuery("from DrugManufactorScientistInteraction");
					
	
					List<DrugManufactorScientistInteraction> list = query.list();
					
					tx.commit();
					return list;
				} catch (Exception e) {
					tx.rollback();
					
				} 

			} catch (HibernateException e) {
				rollback();
				throw new AdException("Exception while creating user: " + e.getMessage());
			}
			return null;
		}
	 
	 
	 public List<DrugManufactorScientistInteraction> getListByStatus(String status)
	         throws AdException{
			try {
				begin();

				Transaction tx;
				tx = getSession().beginTransaction();
				try {
					Query query = getSession().createQuery("from DrugManufactorScientistInteraction dsi where dsi.status=:status");
				    query.setString("status", status);
					List<DrugManufactorScientistInteraction> list = query.list();	
					tx.commit();
					return list;
				} catch (Exception e) {
					tx.rollback();
					
				} 

			} catch (HibernateException e) {
				rollback();
				throw new AdException("Exception while creating user: " + e.getMessage());
			}
			return null;
		}
	 
	 public void update(DrugManufactorScientistInteraction drugManufactorScientistInteraction) throws Exception {
	        try {
	            begin();
	            getSession().update(drugManufactorScientistInteraction);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new Exception("Could not save the category", e);
	        }
	    }

}
