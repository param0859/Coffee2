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
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/coffeeshop")
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
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
				listStudents(request, response);
				break;
				
			case "ADD":
				addStudent(request, response);
				break; 
				
			case "LOAD":
				loadStudent(request, response);
				break;
				
			case "UPDATE":
				updateStudent(request, response);
				break;
				
			case "DELETE":
				deleteStudent(request, response);
				break;
				
			default:
				listStudents(request, response);
				
			}
			
			
			
			
	//List the students
			
		
	
	
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
		
	}




	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception
	
	{
		//read student 
		String theStudentId = request.getParameter("studentId");
			
		
		//Delete from database 
		studentDbUtil.deleteStudent(theStudentId);
		
		//send them back to student 
		listStudents(request, response);
		
		
	}




	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read 
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String manager = request.getParameter("manager");
	
		
		//create 
		Student theStudent = new Student(id, firstName, lastName, email, phone, manager);
		
		//perform update
		studentDbUtil.updateStudent(theStudent);
		
		//send back to list 
		listStudents(request, response);
		
	}




	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			
		//read
		String theStudentId = request.getParameter("studentId");
		
		
		
		//get
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		
		
		//place
		request.setAttribute("THE_STUDENT", theStudent);
		
		
		//send
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
			
			
		}
		
		
		
	




	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		// read
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String manager = request.getParameter("manager");
		
		
		//create 
		Student theStudent = new Student(firstName, lastName, email, phone, manager);
		
		
		//add
		studentDbUtil.addStudent(theStudent);
		
		//add to list 
		
		listStudents(request, response);
		
		
	}




	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		//get students
		List<Student> students = studentDbUtil.getStudents();
		
		//add students
		request.setAttribute("STUDENT_LIST", students);
		
		//send to JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
		
	}

}
