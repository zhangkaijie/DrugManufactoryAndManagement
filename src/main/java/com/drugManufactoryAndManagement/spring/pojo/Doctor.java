package com.drugManufactoryAndManagement.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "doctor")
@PrimaryKeyJoinColumn(name = "userId")
public class Doctor extends UserAccount{
	 
	
   public Doctor (String username, String password) {
	      super(username,password);
	      	      
   }

   public Doctor () {
	   
   }
	
}
