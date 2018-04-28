<!-- <%@ page import="java.util.*, com.cmpe172.web.jdbc.*" %> // -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Student Tracker App</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>


<%-- <%
	//get
	List<Student> theStudents = (List<Student>) request.getAttribute("STUDENT_LIST");
	
%> --%>

<body>

  <%

	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	 /* response.setHeader("Expires", "0");  */
	

	if(session.getAttribute("username") == null)
	{
		response.sendRedirect("login.jsp");
	}

%>

Welcome ${username}	  
<div id="wrapper">
<div id="header">
<h2>SJSU CMPE 172</h2>
</div>
</div>

<div id="container">

<div id ="content">

<!-- Button for Add Student -->
<input type ="button" value ="Add Student" onclick="window.location.href='add-student-form.jsp'; return false;" class ="add-student-button"/>
<table>

<tr> 
	<th>First Name</th>
	<th>Last Name</th>
	<th>Email</th>
	<th>Phone</th>
	<th>Manager</th>
	<th>Action</th>
	
</tr>

<%--  <%for (Student tempStudent : theStudents) { %> --%>
<c:forEach var="tempStudent" items="${STUDENT_LIST}">


<c:url var="tempLink" value="StudentControllerServlet">
	<c:param name="command" value="LOAD"/>
	<c:param name="studentId" value="${tempStudent.id}" />
</c:url>

<!--  setup link to delete student -->
<c:url var="deleteLink" value="StudentControllerServlet">
	<c:param name="command" value="DELETE"/>
	<c:param name="studentId" value="${tempStudent.id}" />
</c:url>

<tr>
	<td> ${tempStudent.firstName} </td>
	<td> ${tempStudent.lastName} </td>
	<td> ${tempStudent.email}  </td>
	<td> ${tempStudent.phone}  </td>
	<td> ${tempStudent.manager}  </td>
	<td> 
	
	<a href="${tempLink}">Update</a> |
	 <a href="${deleteLink}" onclick="if (!(confirm('Are you sure'))) return false">Delete</a>
	</td>
	

</tr>

</c:forEach> 
</table>



</div>
</div>

 <form action="Logout">
<input type="submit" value="Logout">

</form>  

</body>

</html>