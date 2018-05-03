package com.cmpe172.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Define dataSource
	
	@Resource(name="jdbc/coffeeshop")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//PrintWriter 
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		
		//Connection to DB 
		java.sql.Connection myConn = null;
		java.sql.Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
		//Create SQL Statements
			String sql = "select * from employee";
			myStmt = myConn.createStatement();
		
		//Execute SQL 
		myRs = myStmt.executeQuery(sql);
		//Process Results 
	while (myRs.next())
	{
		String email = myRs.getString("email");
		out.println(email);
	}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
		
		
		
	}


