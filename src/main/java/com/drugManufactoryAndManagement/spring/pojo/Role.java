package com.drugManufactoryAndManagement.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "roleId", unique=true, nullable = false)
	private long roleId;

	@Column(name = "roleName")
	private String roleName;

     
	public Role() {
		
	}
    public Role(String rolename) {
    	this.roleName=rolename;
    }
	public long getRoleid() {
		return roleId;
	}

	public void setRoleid(long roleid) {
		this.roleId = roleid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	
	
}
