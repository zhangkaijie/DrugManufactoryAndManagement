package com.drugManufactoryAndManagement.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Patient")
@PrimaryKeyJoinColumn(name = "userId")
public class Patient extends UserAccount {

	
	   public Patient (String username, String password) {
		      super(username,password);
		      	      
	   }
	
	   public Patient () {
		   
	   }

	


 	
	
	
}
