package com.drugManufactoryAndManagement.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "drugManufactor")
@PrimaryKeyJoinColumn(name = "userId")
public class DrugManufactor extends UserAccount {
       
   public DrugManufactor (String username, String password) {
	      super(username,password);
	      	      
   }

   public DrugManufactor () {
	   
   }
	
	
}
