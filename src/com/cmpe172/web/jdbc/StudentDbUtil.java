package com.cmpe172.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource dataSource;
	

	public StudentDbUtil(DataSource theDataSource)
	{
		dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception {
		
		
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
		//get a connection 
			myConn = dataSource.getConnection();
		
		//Create SQL statement
		
			String sql = "select * from student order by last_name";
			
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
				
				
				//new student 
				Student tempStudent = new Student(id, firstName, lastName, email, phone, manager);
				
				//add to list 
				students.add(tempStudent);
			}
		
		//Close JDBC objects 
		
			return students;
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

	public void addStudent(Student theStudent) throws Exception{
		// TODO Auto-generated method stub
		
		Connection myConn = null; 
		PreparedStatement myStmt = null; 
		
		try {
			
			myConn = dataSource.getConnection();
		//insert to SQL 
			String sql = "insert into student " 
					+ "(first_name, last_name, email, phone, manager) "
					+ "values (?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
		
		//parameter values for student 
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setString(4, theStudent.getPhone());
			myStmt.setString(5, theStudent.getManager());
			
			
		
		//execute sql insert 
		
			myStmt.execute();
		//clean JDBC 
			
		}
		
		finally {
			
			close(myConn, myStmt, null);
			
		}
		
		
		
		
		
		
		
	}

	public Student getStudent(String theStudentId) throws Exception {
		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			
			//convert
			studentId = Integer.parseInt(theStudentId);
			
			
			//get connection 
			
			myConn = dataSource.getConnection();
			//create SQL 
			
			String sql = "select * from student where id=?";
			//create prepared statement 
			
			myStmt = myConn.prepareStatement(sql);
			//set params 
			myStmt.setInt(1, studentId);
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
				
				theStudent = new Student(studentId, firstName, lastName, email, phone, manager);
				
			}
			
			else {
				throw new Exception("Not found " + studentId);
			}
			
			
			return theStudent;
		}
		
		finally {
			
			close(myConn, myStmt, myRs);
			
		}
		
		
				
		
	}

	public void updateStudent(Student theStudent) throws Exception {
		Connection myConn = null; 
		PreparedStatement myStmt = null;
		
		try {
			//get db 
			myConn = dataSource.getConnection();
			
			//create
			String sql = "update student " + "set first_name=?, last_name=?, email=?, phone=?, manager=?" + "where id=?";
			
			//preapre
			myStmt = myConn.prepareStatement(sql);
			
			//params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setString(4, theStudent.getPhone());
			myStmt.setString(5, theStudent.getManager());
			myStmt.setInt(6, theStudent.getId());
			
			
			//execute
			myStmt.execute();
		}
	finally {
		close(myConn, myStmt, null);
	}
		
		
	}

	public void deleteStudent(String theStudentId) throws Exception {
		// TODO Auto-generated method stub
		
		Connection myConn = null; 
		PreparedStatement myStmt = null;
		
		try {
			
			//convert studentId to int 
			int studentId = Integer.parseInt(theStudentId);
			//Get connection to database 
			myConn = dataSource.getConnection();
			
			//SQL to delete 
			String sql = "delete from student where id=?";
			
			//prepare stmt 
			myStmt = myConn.prepareStatement(sql);
			
			//set params 
			myStmt.setInt(1, studentId);
			
			//execute sql 
			
			myStmt.execute();
			
			
		}
		
		finally {
			
			//clean up 
			
			close(myConn, myStmt, null);
		}
		
		
	}
	
	
}
