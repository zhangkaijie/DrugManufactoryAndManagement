package com.drugManufactoryAndManagement.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.drugManufactoryAndManagement.spring.exception.AdException;
import com.drugManufactoryAndManagement.spring.pojo.DoctorScientistInteraction;
import com.drugManufactoryAndManagement.spring.pojo.PatientDoctorInteraction;

public class DoctorScientistInteractionDAO extends DAO{

	 public DoctorScientistInteraction create(DoctorScientistInteraction doctorScientistInteraction) 
			   throws AdException{
				 try {
			            begin();            
			            getSession().save(doctorScientistInteraction);     
			            commit();
			            return doctorScientistInteraction;
			        } catch (HibernateException e) {
			            rollback();
			            throw new AdException("Exception while creating advert: " + e.getMessage());
			        }
			 }
			 
			public DoctorScientistInteraction getById(int id) throws Exception {
				try {
					begin();

					Transaction tx;
					tx = getSession().beginTransaction();
					try {
						Query query = getSession().createQuery("from DoctorScientistInteraction dsi where dsi.doctorScientistId=:id");
						query.setInteger("id", id);
						DoctorScientistInteraction doctorScientistInteraction = (DoctorScientistInteraction)query.uniqueResult();
						
						tx.commit();
						return doctorScientistInteraction;
					} catch (Exception e) {
						tx.rollback();
						
					} 

				} catch (HibernateException e) {
					rollback();
					throw new Exception("Exception while creating user: " + e.getMessage());
				}
				return null;
			}
			     
			 
			 public List<DoctorScientistInteraction> getList()
			         throws AdException{
					try {
						begin();

						Transaction tx;
						tx = getSession().beginTransaction();
						try {
							Query query = getSession().createQuery("from DoctorScientistInteraction");
							
			
							List<DoctorScientistInteraction> list = query.list();
							
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
			 
			 public List<DoctorScientistInteraction> getListByDisease(String disease)
			         throws AdException{
					try {
						begin();

						Transaction tx;
						tx = getSession().beginTransaction();
						try {
							Query query = getSession().createQuery("from DoctorScientistInteraction dsi where dsi.disease=:disease");
						    query.setString("disease", disease);
						   
							List<DoctorScientistInteraction> list = query.list();
							List<DoctorScientistInteraction> list2 = new ArrayList<DoctorScientistInteraction>();
							for (DoctorScientistInteraction dsi : list) {
								if (dsi.getStatus().equals("processing")) {
									list2.add(dsi);
								}
							}
							tx.commit();
							return list2;
						} catch (Exception e) {
							tx.rollback();
							
						} 

					} catch (HibernateException e) {
						rollback();
						throw new AdException("Exception while creating user: " + e.getMessage());
					}
					return null;
				}

			 public void update(DoctorScientistInteraction doctorScientistInteraction) throws Exception {
			        try {
			            begin();
			            getSession().update(doctorScientistInteraction);
			            commit();
			        } catch (HibernateException e) {
			            rollback();
			            throw new Exception("Could not save the category", e);
			        }
		  }
		
			 public List<DoctorScientistInteraction> getListById(int scientistid)
			         throws AdException{
					try {
						begin();

						Transaction tx;
						tx = getSession().beginTransaction();
						try {
							Query query = getSession().createQuery("from DoctorScientistInteraction dsi where dsi.scientist.userId=:scientistid");
							query.setInteger("scientistid", scientistid);
							List<DoctorScientistInteraction> list = query.list();
							
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
			  

	
}
