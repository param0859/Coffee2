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
// the servlet is used as the url displayed after local host
@WebServlet("/EmployeeControllerServlet")

public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   // setting up the serialization id


	private EmployeeDbUtil employeeDbUtil;   // private local variable

	@Resource(name="jdbc/coffeeshop")   // name of our database is used as resource
	
	private DataSource dataSource;    // private local variable


	@Override
	public void init() throws ServletException {   // initializer method
		// TODO Auto-generated method stub
		super.init();

		try {
			employeeDbUtil = new EmployeeDbUtil(dataSource);  // creating an instance of EmployeeDbUtil class
		}

		catch (Exception e)
		{
			throw new ServletException(e);  // throws Servlet exception
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	// doGet method is implemented as a required method for HTTP servlet as we are sending a get request to the server
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//read the command 
			String theCommand = request.getParameter("command");  // request the command parameter

			if(theCommand == null)  // if command == null, just show the employee list to the user
			{
				theCommand = "LIST";
			}

			 // using switch statement to use as route to method
			switch(theCommand) { 

			case "LIST":      // just shows the employee list
				listEmployees(request, response);
				break;

			case "ADD":   // takes user to add employee page
				addEmployee(request, response);
				break; 

			case "LOAD":   // loads the employee page
				loadEmployee(request, response);
				break;

			case "UPDATE":    // takes the user to the update employee info page
				updateEmployee(request, response);
				break;

			case "DELETE":    // deleteEmployee method is called and asks the user if he wants to delete data
				deleteEmployee(request, response);
				break;

			case "SEARCH":    // searches for a particular employee
				searchEmployees(request, response);
				break;

			default:   // if nothing else works, just shows the list of employees as default
				listEmployees(request, response);

			}

		}
		catch (Exception e) {
			throw new ServletException(e);  // throws servletexception
		}

	}

// this method searches an employee's info by taking either the first or last name as query paramter
	private void searchEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub


		String theSearchName = request.getParameter("theSearchName");  // name is used as the search parameter

		// create a list of employees based on their first or last name queried
		List<Employee> employees = employeeDbUtil.searchEmployees(theSearchName);  


		request.setAttribute("EMPLOYEE_LIST", employees);  // sets the Employee_List attribute

// shows the list-employee.jsp containing the searched employees
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employee.jsp");
		dispatcher.forward(request, response);   // dispatcher forwards the request
	}



// this method deletes a particular employee's data from the portal and the database
	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception

	{
		//read employee 
		String theEmployeeId = request.getParameter("employeeId");  // employeeId used a request paramter


		//Delete from database 
		employeeDbUtil.deleteEmployee(theEmployeeId);  // calls deleteEmployee method from EmployeeDbUtil class

		//send them back to employee 
		listEmployees(request, response);    // lists the remaining employees

	}

	// update current employee's information
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//read all the 6 required query paramters
		int id = Integer.parseInt(request.getParameter("employeeId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String manager = request.getParameter("manager");


		//create an instance of Employee class by calling the constructor
		Employee theEmployee = new Employee(id, firstName, lastName, email, phone, manager);

		//perform update
		employeeDbUtil.updateEmployee(theEmployee);

		//send back to list 
		listEmployees(request, response);

	}

// this method loads the employee list from database
	private void loadEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//read based on employeeId parameter
		String theEmployeeId = request.getParameter("employeeId");

		//gets the Employee info
		Employee theEmployee = employeeDbUtil.getEmployee(theEmployeeId);


		//sets the attribute
		request.setAttribute("THE_EMPLOYEE", theEmployee);


		//sends the request and takes the user to update-employee-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-employee-form.jsp");
		dispatcher.forward(request, response);  // dispatches the request forward


	}
	
// this method adds the employee to the database
	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {


		// read all the required 6 parameters
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String manager = request.getParameter("manager");

		// check for validation if first name is empty
		if(request.getParameter("firstName").isEmpty())
		{
			// stay on the add page
			RequestDispatcher dispatcher = request.getRequestDispatcher("/add-employee-form.jsp");
			dispatcher.forward(request, response);
		}

		// check for validation if last name is empty
		else if(request.getParameter("lastName").isEmpty())
		{
			// stay on the add page
			RequestDispatcher dispatcher = request.getRequestDispatcher("/add-employee-form.jsp");
			dispatcher.forward(request, response);
		}

		// check for validation if email is empty
		else if(request.getParameter("email").isEmpty())
		{
			// stay on the add employee page
			RequestDispatcher dispatcher = request.getRequestDispatcher("/add-employee-form.jsp");
			dispatcher.forward(request, response);
		}


		// check for validation if phone number is empty
		else if(request.getParameter("phone").isEmpty())
		{
			// stay on the add employee page
			RequestDispatcher dispatcher = request.getRequestDispatcher("/add-employee-form.jsp");
			dispatcher.forward(request, response);
		}

		// check for validation if manager is empty		
		else if(request.getParameter("manager").isEmpty())
		{
			// stay on the add employee page
			RequestDispatcher dispatcher = request.getRequestDispatcher("/add-employee-form.jsp");
			dispatcher.forward(request, response);
		}
		
		else {

			// creating an instance of Employee class by calling the constructor 
			Employee theEmployee = new Employee(firstName, lastName, email, phone, manager);

// if validation is passed, add the employee to the database
			employeeDbUtil.addEmployee(theEmployee);

 // shows the updated list to the user on the portal screen
			listEmployees(request, response);
			System.out.println(theEmployee);  // prints the employee added for debugging purposes

		}
	}



// this method just shows the employee list to the user
	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		//get employee from the EmployeeDbUtil class method
		List<Employee> employees = employeeDbUtil.getEmployees();

		//adds employee by setting the attribute
		request.setAttribute("EMPLOYEE_LIST", employees);

		//send to JSP through the dispatcher and displays list of employees
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employee.jsp");
		dispatcher.forward(request, response);  // dispatches the request forward

	}

}
