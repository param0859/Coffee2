package com.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class LoginDatabase {
	
	String sql = "select * from login where uname=? and pass=?";
	String url = "jdbc:mysql://localhost:3306/coffeeshop";
	String username ="root";
	String password = "123456";
	public boolean check(String uname, String pass) throws SQLException
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, uname);
			st.setString(2, pass);
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				return true;
			}
		
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

}
