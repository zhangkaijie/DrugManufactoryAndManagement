package com.drugManufactoryAndManagement.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "scientist")
@PrimaryKeyJoinColumn(name = "userId")
public class Scientist extends UserAccount{
	
	
   public Scientist (String username, String password) {
	      super(username,password);
	      	      
   }

   public Scientist () {
	   
   }
    
}
