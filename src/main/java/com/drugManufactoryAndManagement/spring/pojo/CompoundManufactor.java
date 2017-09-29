package com.drugManufactoryAndManagement.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "compoundManufactor")
@PrimaryKeyJoinColumn(name = "userId")
public class CompoundManufactor extends UserAccount{
	   
	  

   public CompoundManufactor (String username, String password) {
	      super(username,password);
	      	      
   }

   public CompoundManufactor () {
	   
   }
	   

}
