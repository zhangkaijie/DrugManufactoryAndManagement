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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "patientDoctorInteraction")
public class PatientDoctorInteraction {
	
	   @Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name = "patientDoctorId", unique=true, nullable = false)
	   private int patientDoctorId;
	   
	   @ManyToOne
	   @JoinColumn(name="patientid")   
	  // @Cascade({CascadeType.MERGE, CascadeType.SAVE_UPDATE})
       private Patient patient;
	   
	   @ManyToOne
	   @JoinColumn(name="doctorid")
	//   @Cascade({CascadeType.MERGE, CascadeType.SAVE_UPDATE})
       private Doctor doctor;
	   
	   @Column(name ="diseaseDescription")
       private String diseaseDescription;
	   
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


	public String getDiseaseDescription() {
		return diseaseDescription;
	}


	public void setDiseaseDescription(String diseaseDescription) {
		this.diseaseDescription = diseaseDescription;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Column(name ="drug")
       private String drug;
	   
	   
	   @Column(name ="disease")
       private String disease;
 
	   @Column(name ="cost")
       private float cost;

	public float getCost() {
		return cost;
	}


	public void setCost(float cost) {
		this.cost = cost;
	}


	public int getPatientDoctorId() {
		return patientDoctorId;
	}


	public void setPatientDoctorId(int patientDoctorId) {
		this.patientDoctorId = patientDoctorId;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
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
       
       
}
