package com.drugManufactoryAndManagement.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.CompoundDrugInteraction;
import com.drugManufactoryAndManagement.spring.pojo.DoctorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;

public class CompoundDrugInteractionDAO extends DAO {

	 public CompoundDrugInteraction create(CompoundDrugInteraction compoundDrugInteraction) 
			   throws AdException{
				 try {
			            begin();            
			            getSession().save(compoundDrugInteraction);     
			            commit();
			            return compoundDrugInteraction;
			        } catch (HibernateException e) {
			            rollback();
			            throw new AdException("Exception while creating advert: " + e.getMessage());
			        }
			 }
	 
	 public List<CompoundDrugInteraction> getList()
	         throws AdException{
			try {
				begin();

				Transaction tx;
				tx = getSession().beginTransaction();
				try {
					Query query = getSession().createQuery("from CompoundDrugInteraction");
					
	
					List<CompoundDrugInteraction> list = query.list();
					
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
	 
	 public List<CompoundDrugInteraction> getListByStatus(String status)
	         throws AdException{
			try {
				begin();

				Transaction tx;
				tx = getSession().beginTransaction();
				try {
					Query query = getSession().createQuery("from CompoundDrugInteraction cdi where cdi.status=:status");
				    query.setString("status", status);
					List<CompoundDrugInteraction> list = query.list();	
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

	 public void update(CompoundDrugInteraction compoundDrugInteraction) throws Exception {
	        try {
	            begin();
	            getSession().update(compoundDrugInteraction);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new Exception("Could not save the category", e);
	        }
	    }

}
