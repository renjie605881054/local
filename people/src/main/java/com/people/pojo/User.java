package com.people.pojo;

import java.io.Serializable;

public class User implements Serializable{

	private String userName;
	private String userId;
	private String email;
	private String password;

	
	public User(){
		
	}
	
	public User(String userName){
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userId=" + userId + ", email="
				+ email + ", password=" + password + "]";
	}

	
	

}
