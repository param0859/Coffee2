package com.cmpe172.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EmployeeDbUtil {
	
	private DataSource dataSource;
	

	public EmployeeDbUtil(DataSource theDataSource)
	{
		dataSource = theDataSource;
	}
	
	public List<Employee> getEmployees() throws Exception {
		
		
		List<Employee> employees = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
		//get a connection 
			myConn = dataSource.getConnection();
		
		//Create SQL statement
		
			String sql = "select * from employee order by last_name";
			
			myStmt = myConn.createStatement();
		//Execute Query  
			myRs = myStmt.executeQuery(sql);
		
		//Process 
			while(myRs.next())
			{
				//retrieve data 
				
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String phone = myRs.getString("phone");
				String manager = myRs.getString("manager");
				
				
				
				Employee tempEmployee = new Employee(id, firstName, lastName, email, phone, manager);
				
				//add to list 
				employees.add(tempEmployee);
			}
		
		//Close JDBC objects 
		
			return employees;
		}
		finally {
			
			close(myConn, myStmt, myRs);
			
			
		}
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		
		try {
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
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void addEmployee(Employee theEmployee) throws Exception{
		// TODO Auto-generated method stub
		
		Connection myConn = null; 
		PreparedStatement myStmt = null; 
		
		try {
			
			myConn = dataSource.getConnection();
		//insert to SQL 
			String sql = "insert into employee " 
					+ "(first_name, last_name, email, phone, manager) "
					+ "values (?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
		
		
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
			
			close(myConn, myStmt, null);
			
		}
		
		
		
		
		
		
		
	}

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
			
			else {
				throw new Exception("Not found " + employeeId);
			}
			
			
			return theEmployee;
		}
		
		finally {
			
			close(myConn, myStmt, myRs);
			
		}
		
		
				
		
	}

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
		close(myConn, myStmt, null);
	}
		
		
	}

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
			
			//clean up 
			
			close(myConn, myStmt, null);
		}
		
		
	}
	
	
	 public List<Employee> searchEmployees(String theSearchName)  throws Exception {
	        List<Employee> employees = new ArrayList<>();
	        
	        Connection myConn = null;
	        PreparedStatement myStmt = null;
	        ResultSet myRs = null;
	        int employeeId;
	        
	        try {
	            
	            
	            myConn = dataSource.getConnection();
	            
	          
	            if (theSearchName != null && theSearchName.trim().length() > 0) {
	               
	                String sql = "select * from employee where lower(first_name) like ? or lower(last_name) like ?";
	                
	                myStmt = myConn.prepareStatement(sql);
	                
	                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
	                myStmt.setString(1, theSearchNameLike);
	                myStmt.setString(2, theSearchNameLike);
	                
	            } else {
	                
	                String sql = "select * from employee order by last_name";
	                
	                myStmt = myConn.prepareStatement(sql);
	            }
	            
	           
	            myRs = myStmt.executeQuery();
	            
	            
	            while (myRs.next()) {
	                
	                
	                int id = myRs.getInt("id");
	                String firstName = myRs.getString("first_name");
	                String lastName = myRs.getString("last_name");
	                String email = myRs.getString("email");
	                String phone = myRs.getString("phone");
	                String manager = myRs.getString("manager");
	                
	               
	                Employee tempEmployee = new Employee(id, firstName, lastName, email, phone, manager);
	                
	                
	                employees.add(tempEmployee);            
	            }
	            
	            return employees;
	        }
	        finally {
	            
	            close(myConn, myStmt, myRs);
	        }
	    }
	
	
}
