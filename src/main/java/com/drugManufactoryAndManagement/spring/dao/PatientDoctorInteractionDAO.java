package com.drugManufactoryAndManagement.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.Email;
import com.drugManufactoryAndManagement.spring.pojo.Patient;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;
import com.drugManufactoryAndManagement.spring.pojo.UserAccount;






public class PatientDoctorInteractionDAO extends DAO{
       
	  
	   
	 public PatientDoctorInteraction create(PatientDoctorInteraction patientDoctorInteraction) 
	   throws AdException{
		 try {
	            begin();            
	            getSession().save(patientDoctorInteraction);     
	            commit();
	            return patientDoctorInteraction;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create advert", e);
	            throw new AdException("Exception while creating advert: " + e.getMessage());
	        }
	 }
	 
	public PatientDoctorInteraction getById(int id) throws Exception {
		try {
			begin();

			Transaction tx;
			tx = getSession().beginTransaction();
			try {
				Query query = getSession().createQuery("from PatientDoctorInteraction pdi where pdi.patientDoctorId=:id");
				query.setInteger("id", id);
				PatientDoctorInteraction patientDoctorInteraction = (PatientDoctorInteraction)query.uniqueResult();
				
				tx.commit();
				return patientDoctorInteraction;
			} catch (Exception e) {
				tx.rollback();
				
			} 

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
		return null;
	}
	     
	 
	 public List<PatientDoctorInteraction> getListById(int patientid)
	         throws AdException{
			try {
				begin();

				Transaction tx;
				tx = getSession().beginTransaction();
				try {
					Query query = getSession().createQuery("from PatientDoctorInteraction pdi where pdi.patient.userId=:patientid");
					query.setInteger("patientid", patientid);
					//@SuppressWarnings("unchecked")
					List<PatientDoctorInteraction> list = query.list();
					
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
	 
	 public List<PatientDoctorInteraction> getListByStatus(String status)
	         throws AdException{
			try {
				begin();

				Transaction tx;
				tx = getSession().beginTransaction();
				try {
					Query query = getSession().createQuery("from PatientDoctorInteraction pdi where pdi.status=:status");
				    query.setString("status", status);
					List<PatientDoctorInteraction> list = query.list();	
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

	 public List<PatientDoctorInteraction> getListByDoctorId(int doctorid)
	         throws AdException{
		 try {
				begin();

				Transaction tx;
				tx = getSession().beginTransaction();
				try {
					Query query = getSession().createQuery("from PatientDoctorInteraction pdi where pdi.doctor.userId=:doctorid");
					query.setInteger("doctorid", doctorid);
					//@SuppressWarnings("unchecked")
					List<PatientDoctorInteraction> list = query.list();
					
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
	 
	 public void update(PatientDoctorInteraction patientDoctorInteraction) throws Exception {
	        try {
	            begin();
	            getSession().update(patientDoctorInteraction);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new Exception("Could not save the category", e);
	        }
	    }

}
