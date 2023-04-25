package com.example.demo.model;

import org.springframework.context.annotation.Primary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity  
@Table(name="lic_user_details")
@Getter
@Setter
public class LicUserEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String address;
	
	@Column
	private int contact;
	
	@Column
	private String email;
	
	public void setUserId(int userId) {
		this.userId=userId;
	}
	
	public void setUserName(String userName) {
		this.userName=userName;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setAddress(String address) {
		this.address=address;
	}
	
	public void setContact(int contact) {
		this.contact=contact;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getContact() {
		return contact;
	}
	
	public String getEmail() {
		return email;
	}
	

}

