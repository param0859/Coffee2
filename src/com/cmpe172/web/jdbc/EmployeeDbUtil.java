package com.cmpe172.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

// creating the EmployeeDbUtil class
public class EmployeeDbUtil {
	
	private DataSource dataSource;  // private variable
	
// constructor of the class
	public EmployeeDbUtil(DataSource theDataSource)  
	{
		dataSource = theDataSource;
	}
	
	// getEmployees method gets the employees from database and returns alist of employees
	public List<Employee> getEmployees() throws Exception {
		
		// creating an empty array list
		List<Employee> employees = new ArrayList<>();
		
		// setting Connection, Statement and ResultSet to null
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
		//get a connection using the data source
			myConn = dataSource.getConnection();
		
		//Create SQL statement
		
			String sql = "select * from employee order by last_name";
			
			myStmt = myConn.createStatement();
		//Execute Query  
			myRs = myStmt.executeQuery(sql);
		
		//Process the query
			while(myRs.next())
			{
				//retrieve data from the database using the parameter strings
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String phone = myRs.getString("phone");
				String manager = myRs.getString("manager");
				
				
				// create an instance of employee using Employee's class constructor
				Employee tempEmployee = new Employee(id, firstName, lastName, email, phone, manager);
				
				//add to list 
				employees.add(tempEmployee);
			}
		
		
			return employees;  // returns a list of employee
		}
		finally {
			//Close JDBC objects by calling close method
			close(myConn, myStmt, myRs);		
		}		
	}

	// close the connection and jdbc object
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		
		try {   // check if its not null
			if (myRs != null)
			{
				myRs.close();
			}
			
			if (myStmt != null)
			{
				myStmt.close();
			}
			
			if (myConn != null)
			{
				myConn.close();
			}
		}
		
		catch (Exception e)  // throws exception
		{
			e.printStackTrace();   // prints the stack trace
		}
		
	}

	// adds the employee to the databse
	public void addEmployee(Employee theEmployee) throws Exception{
		
		
		Connection myConn = null; 
		PreparedStatement myStmt = null; 
		
		try {
			// gets connection using data source
			myConn = dataSource.getConnection();
		//insert to SQL 
			String sql = "insert into employee " 
					+ "(first_name, last_name, email, phone, manager) "
					+ "values (?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);  // prepares sql statement
		
// sets the required string parameters using the Employee class
			myStmt.setString(1, theEmployee.getFirstName());
			myStmt.setString(2, theEmployee.getLastName());
			myStmt.setString(3, theEmployee.getEmail());
			myStmt.setString(4, theEmployee.getPhone());
			myStmt.setString(5, theEmployee.getManager());
			
			
		
		//execute sql insert 
		
			myStmt.execute();
		//clean JDBC 
			
		}
		
		finally {
			// close jdbc object by calling close method
			close(myConn, myStmt, null);
			
		}
		
	}

	// returns Employees from database of type Employee
	public Employee getEmployee(String theEmployeeId) throws Exception {
		Employee theEmployee = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int employeeId;
		
		try {
			
			//convert
			employeeId = Integer.parseInt(theEmployeeId);
			
			
			//get connection 
			
			myConn = dataSource.getConnection();
			//create SQL 
			
			String sql = "select * from employee where id=?";
			//create prepared statement 
			
			myStmt = myConn.prepareStatement(sql);
			//set params 
			myStmt.setInt(1, employeeId);
			//excecute
			myRs = myStmt.executeQuery();
			
			//retrieve 
			if (myRs.next())
			{
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String phone = myRs.getString("phone");
				String manager = myRs.getString("manager");
				
				theEmployee = new Employee(employeeId, firstName, lastName, email, phone, manager);
				
			}
			
			else {  // else throws exception
				throw new Exception("Not found " + employeeId);
			}
			
			return theEmployee;
		}
		
		finally {
			// calls close method
			close(myConn, myStmt, myRs);
			
		}
	
	}

	// updates employee info in the database
	public void updateEmployee(Employee theEmployee) throws Exception {
		Connection myConn = null; 
		PreparedStatement myStmt = null;
		
		try {
			//get db 
			myConn = dataSource.getConnection();
			
			//create
			String sql = "update employee " + "set first_name=?, last_name=?, email=?, phone=?, manager=?" + "where id=?";
			
			//preapre
			myStmt = myConn.prepareStatement(sql);
			
			//params
			myStmt.setString(1, theEmployee.getFirstName());
			myStmt.setString(2, theEmployee.getLastName());
			myStmt.setString(3, theEmployee.getEmail());
			myStmt.setString(4, theEmployee.getPhone());
			myStmt.setString(5, theEmployee.getManager());
			myStmt.setInt(6, theEmployee.getId());
			
			
			//execute
			myStmt.execute();
		}
	finally {
		// close the jdbc object
		close(myConn, myStmt, null);
		}

	}
	
	
// deletes a particular employee from databse
	public void deleteEmployee(String theEmployeeId) throws Exception {
		// TODO Auto-generated method stub
		
		Connection myConn = null; 
		PreparedStatement myStmt = null;
		
		try {
			
			//convert employeeId to int 
			int employeeId = Integer.parseInt(theEmployeeId);
			//Get connection to database 
			myConn = dataSource.getConnection();
			
			//SQL to delete 
			String sql = "delete from employee where id=?";
			
			//prepare stmt 
			myStmt = myConn.prepareStatement(sql);
			
			//set params 
			myStmt.setInt(1, employeeId);
			
			//execute sql 
			
			myStmt.execute();
			
			
		}
		
		finally {
			
			//clean up and close the jdbc 
			
			close(myConn, myStmt, null);
		}
		
		
	}
	
	// returns a list of employees based on the search query
	 public List<Employee> searchEmployees(String theSearchName)  throws Exception {
	        List<Employee> employees = new ArrayList<>();  //empty array list created
	        
	        Connection myConn = null;
	        PreparedStatement myStmt = null;
	        ResultSet myRs = null;
	 //       int employeeId;
	        
	        try {
	           
	            myConn = dataSource.getConnection();  // get connection from datasource
	            
	          // checks if name is not null and length > 0
	            if (theSearchName != null && theSearchName.trim().length() > 0) {
	               
	            	// sql query
	                String sql = "select * from employee where lower(first_name) like ? or lower(last_name) like ?";
	                
	                // prepare statement
	                myStmt = myConn.prepareStatement(sql);
	                
	                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
	                myStmt.setString(1, theSearchNameLike);
	                myStmt.setString(2, theSearchNameLike);
	                
	            } else {
	                // sql statement order by last name
	                String sql = "select * from employee order by last_name";
	                
	                myStmt = myConn.prepareStatement(sql);  // prepare statement
	            }
	           
	            myRs = myStmt.executeQuery();  // executes query
	            
	            
	            while (myRs.next()) {   // process the query
	                
	                
	                int id = myRs.getInt("id");
	                String firstName = myRs.getString("first_name");
	                String lastName = myRs.getString("last_name");
	                String email = myRs.getString("email");
	                String phone = myRs.getString("phone");
	                String manager = myRs.getString("manager");
	                
	               // create instance of employee class using constructor
	                Employee tempEmployee = new Employee(id, firstName, lastName, email, phone, manager);
	                
	                
	                employees.add(tempEmployee);              // adds to the employees list
	            }
	            
	            return employees;  // returns the list of queried employees
	        }
	        finally {
	            // close the jdbc object
	            close(myConn, myStmt, myRs);
	        }
	    } 
	
	
}