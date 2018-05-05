package com.cmpe172.web.jdbc;

// creating an Employee class
public class Employee {

	// declaring private variables
	private int id; 
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String manager;
	
	
	// Employee class constructor with 5 arguments
	public Employee(String firstName, String lastName, String email, String phone, String manager) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.manager = manager;
		
	}

	// Employee class constructor with 6 arguments
	public Employee(int id, String firstName, String lastName, String email, String phone, String manager) {
	
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.manager = manager;
		
	}

	
    // setter for id
	public void setId(int id) {
		this.id = id;
	}

   // getter for id
	public int getId() {
		return id;
	}


    // setter for first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// getter for first name
	public String getFirstName() {
		return firstName;
	}

    
    // setter for last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// getter for last name
	public String getLastName() {
		return lastName;
	}
 
	// setter for email
		public void setEmail(String email) {
			this.email = email;
		}

	// getter for email
	public String getEmail() {
		return email;
	}
    
	// setter for phone
	public void setPhone(String phone) {
		this.phone = phone;
		
	}
	
	// getter for phone
	public String getPhone() {
		return phone;
	}

    // setter for manager
	public void setManager(String manager) {
		this.manager = manager;
	}

	// getter for manager
	public String getManager() {
		return manager;
	}
   
    // to string method to print values for debugging purposes
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone +", manager=" + manager +"]";
	}
	
	
	
}
