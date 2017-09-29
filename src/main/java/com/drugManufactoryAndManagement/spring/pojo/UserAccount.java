package com.drugManufactoryAndManagement.spring.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="UserAccount")
@Inheritance(strategy=InheritanceType.JOINED) //table per subclass
public class UserAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userId", unique=true, nullable = false)
	private int userId;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name ="lastname")
	private String lastname;
	
	@Column(name ="username")
	private String username;
	
	@Column(name ="password")
	private String password;
	
	@OneToOne(mappedBy = "useraccount", cascade = CascadeType.ALL)
	private Email email;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "roleid")
	private Role role;
	
	public UserAccount() {
		
	}
	public UserAccount(String username, String password) {
		this.username=username;
		this.password=password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
}
