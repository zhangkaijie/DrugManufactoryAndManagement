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
@Table(name = "compoundDrugInteraction")
public class CompoundDrugInteraction {

	   @Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "compoundDrugId", unique=true, nullable = false)
	   private int compoundDrugId;
	   
	   @ManyToOne
	   @JoinColumn(name="compoundManufactorid")   
       private CompoundManufactor compoundManufactor;
	   
	   @ManyToOne
	   @JoinColumn(name="drugManufactorid")
       private DrugManufactor drugManufactor;
	   
	   
	   @Column(name ="compound")
       private String compound;


	   @Column(name ="message")
       private String message;
	   
	   @Column(name ="status")
       private String status;
	   
	   
	   
	   @Transient
	   int postedBy;

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getCompoundDrugId() {
		return compoundDrugId;
	}


	public void setCompoundDrugId(int compoundDrugId) {
		this.compoundDrugId = compoundDrugId;
	}


	public CompoundManufactor getCompoundManufactor() {
		return compoundManufactor;
	}


	public void setCompoundManufactor(CompoundManufactor compoundManufactor) {
		this.compoundManufactor = compoundManufactor;
	}


	public DrugManufactor getDrugManufactor() {
		return drugManufactor;
	}


	public void setDrugManufactor(DrugManufactor drugManufactor) {
		this.drugManufactor = drugManufactor;
	}


	public String getCompound() {
		return compound;
	}


	public void setCompound(String compound) {
		this.compound = compound;
	}


	

	public int getPostedBy() {
		return postedBy;
	}


	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}
	   
	   
	   
   
}
