package com.cmpe172.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class EmployeeControllerServlet
 */
@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private EmployeeDbUtil employeeDbUtil;
	
	@Resource(name="jdbc/coffeeshop")
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			employeeDbUtil = new EmployeeDbUtil(dataSource);
		}
		
		catch (Exception e)
		{
			throw new ServletException(e);
		}
	}




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
	//read the command 
			String theCommand = request.getParameter("command");
			
			if(theCommand == null)
			{
				theCommand = "LIST";
			}
	
	//route to method
			
			switch(theCommand) {
			
			case "LIST":
				listEmployees(request, response);
				break;
				
			case "ADD":
				addEmployee(request, response);
				break; 
				
			case "LOAD":
				loadEmployee(request, response);
				break;
				
			case "UPDATE":
				updateEmployee(request, response);
				break;
				
			case "DELETE":
				deleteEmployee(request, response);
				break;
				
			case "SEARCH":
				searchEmployees(request, response);
				break;
				
			default:
				listEmployees(request, response);
				
			}
			
			
			
			
	//List the employee
			
		
	
	
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
		
	}




	private void searchEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
        String theSearchName = request.getParameter("theSearchName");
        
        
        List<Employee> employees = employeeDbUtil.searchEmployees(theSearchName);
        
        
        request.setAttribute("EMPLOYEE_LIST", employees);
                
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employee.jsp");
        dispatcher.forward(request, response);
    }




	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception
	
	{
		//read employee 
		String theEmployeeId = request.getParameter("employeeId");
			
		
		//Delete from database 
		employeeDbUtil.deleteEmployee(theEmployeeId);
		
		//send them back to employee 
		listEmployees(request, response);
		
		
	}




	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read 
		int id = Integer.parseInt(request.getParameter("employeeId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String manager = request.getParameter("manager");
	
		
		//create 
		Employee theEmployee = new Employee(id, firstName, lastName, email, phone, manager);
		
		//perform update
		employeeDbUtil.updateEmployee(theEmployee);
		
		//send back to list 
		listEmployees(request, response);
		
	}




	private void loadEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			
		//read
		String theEmployeeId = request.getParameter("employeeId");
		
		
		
		//get
		Employee theEmployee = employeeDbUtil.getEmployee(theEmployeeId);
		
		
		//place
		request.setAttribute("THE_EMPLOYEE", theEmployee);
		
		
		//send
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-employee-form.jsp");
		dispatcher.forward(request, response);
			
			
		}
		
		
		
	




	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		// read
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String manager = request.getParameter("manager");
		
		if(request.getParameter("firstName").isEmpty())
		{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-employee-form.jsp");
		dispatcher.forward(request, response);
		}


		else if(request.getParameter("lastName").isEmpty())
		{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-employee-form.jsp");
		dispatcher.forward(request, response);
		}

		else if(request.getParameter("email").isEmpty())
		{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-employee-form.jsp");
		dispatcher.forward(request, response);
		}


		else if(request.getParameter("phoneNumber").isEmpty())
		{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-employee-form.jsp");
		dispatcher.forward(request, response);
		}


		else if(request.getParameter("manager").isEmpty())
		{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-employee-form.jsp");
		dispatcher.forward(request, response);
		}
		else {

		
		Employee theEmployee = new Employee(firstName, lastName, email, phone, manager);

		
		employeeDbUtil.addEmployee(theEmployee);

		
		listEmployees(request, response);
		System.out.println(theEmployee);
		
		}
	}




	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		//get employee
		List<Employee> employees = employeeDbUtil.getEmployees();
		
		//add employee
		request.setAttribute("EMPLOYEE_LIST", employees);
		
		//send to JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employee.jsp");
		dispatcher.forward(request, response);
		
	}

}
