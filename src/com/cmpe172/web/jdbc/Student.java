package com.cmpe172.web.jdbc;

public class Student {

	
	private int id; 
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String manager;
	
	
	
	
	
	public Student(String firstName, String lastName, String email, String phone, String manager) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.manager = manager;
		
	}




	public Student(int id, String firstName, String lastName, String email, String phone, String manager) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.manager = manager;
		
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getFirstName() {
		return firstName;
	}

	
	


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManager() {
		return manager;
	}





	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone +", manager=" + manager +"]";
	}
	
	
	
}
