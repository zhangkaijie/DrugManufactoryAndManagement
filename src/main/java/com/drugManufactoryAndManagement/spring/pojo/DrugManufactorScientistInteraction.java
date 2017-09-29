package com.drugManufactoryAndManagement.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "drugManufactorScientistInteraction")
public class DrugManufactorScientistInteraction {
	   @Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "drugManufactorScientistId", unique=true, nullable = false)
	   private int drugManufactorScientistId;
	   
	   @ManyToOne
	   @JoinColumn(name="scientistid")   
       private Scientist scientist;
	   
	   @ManyToOne
	   @JoinColumn(name="drugManufactorid")
       private DrugManufactor drugManufactor;
	   
	   
	   @Column(name ="result")
       private String result;


	   @Column(name ="status")
       private String status;
	   
	   @Transient
	   int postedBy;

	public int getDrugManufactorScientistId() {
		return drugManufactorScientistId;
	}


	public void setDrugManufactorScientistId(int drugManufactorScientistId) {
		this.drugManufactorScientistId = drugManufactorScientistId;
	}


	public Scientist getScientist() {
		return scientist;
	}


	public void setScientist(Scientist scientist) {
		this.scientist = scientist;
	}


	


	public DrugManufactor getDrugManufactor() {
		return drugManufactor;
	}


	public void setDrugManufactor(DrugManufactor drugManufactor) {
		this.drugManufactor = drugManufactor;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getPostedBy() {
		return postedBy;
	}


	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}


		   
	   
	   
   
       
}
