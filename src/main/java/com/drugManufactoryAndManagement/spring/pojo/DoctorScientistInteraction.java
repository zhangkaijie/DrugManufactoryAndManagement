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
@Table(name = "doctorScientistInteraction")
public class DoctorScientistInteraction {

	   @Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "doctorScientistId", unique=true, nullable = false)
	   private int doctorScientistId;
	   
	   @ManyToOne
	   @JoinColumn(name="scientistid")   
       private Scientist scientist;
	   
	   @ManyToOne
	   @JoinColumn(name="doctorid")
       private Doctor doctor;
	   
	   
	   @Column(name ="drug")
       private String drug;
	   
	   @Column(name ="disease")
       private String disease;
	   
	   @Column(name ="gene")
       private String gene;

	   @Column(name ="compound1")
       private String compound1;
	   
	   @Column(name ="compound2")
       private String compound2;

	   @Column(name ="compound3")
       private String compound3;

	   @Column(name ="status")
       private String status;


	   @Transient
	   int postedBy;


	public int getPostedBy() {
		return postedBy;
	}


	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}


	public int getDoctorScientistId() {
		return doctorScientistId;
	}


	public void setDoctorScientistId(int doctorScientistId) {
		this.doctorScientistId = doctorScientistId;
	}


	public Scientist getScientist() {
		return scientist;
	}


	public void setScientist(Scientist scientist) {
		this.scientist = scientist;
	}


	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public String getDrug() {
		return drug;
	}


	public void setDrug(String drug) {
		this.drug = drug;
	}


	public String getDisease() {
		return disease;
	}


	public void setDisease(String disease) {
		this.disease = disease;
	}


	public String getGene() {
		return gene;
	}


	public void setGene(String gene) {
		this.gene = gene;
	}


	public String getCompound1() {
		return compound1;
	}


	public void setCompound1(String compound1) {
		this.compound1 = compound1;
	}


	public String getCompound2() {
		return compound2;
	}


	public void setCompound2(String compound2) {
		this.compound2 = compound2;
	}


	public String getCompound3() {
		return compound3;
	}


	public void setCompound3(String compound3) {
		this.compound3 = compound3;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
     
       
}
