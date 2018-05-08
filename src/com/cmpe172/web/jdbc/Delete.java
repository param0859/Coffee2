package com.cmpe172.web.jdbc;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.junit.Test;

public class Delete {
	private EmployeeDbUtil test1;

	@Resource(name="jdbc/coffeeshop")
	private DataSource dataSource;


	@Test
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		

		try {
			test1 = new EmployeeDbUtil(dataSource);
		}

		catch (Exception e)
		{
			throw new ServletException(e);
		}
	}

	
	public void test() {
		Employee e1= new Employee (3,"Andy", "b", "andy@gmail.com", "408987675", "tom");

		try {
			test1.deleteEmployee(Integer.toString(e1.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
