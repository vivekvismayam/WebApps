package com.javaproject;

public class Volunteer {
	private int eID;
	private String name,email,phone;
	public Volunteer(int eID, String name, String email, String phone) {
		super();
		this.eID = eID;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	public int geteID() {
		return eID;
	}
	public void seteID(int eID) {
		this.eID = eID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean checkVolunteerDataValidity() {
		Boolean dataValidity=true;
		if(name=="") {
			System.out.println("Name cannot be blank");
			dataValidity=false;
		}
		if(email=="") {
			System.out.println("email cannot be blank");
			dataValidity=false;
		}
		if(phone=="") {
			System.out.println("phone cannot be blank");
			dataValidity=false;
		}
		return dataValidity;
		
	}
	
}
