<!DOCTYPE html>
<html>
<head>
<title>Update Student</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">



</head>
<body>
<%

	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	if(session.getAttribute("username") == null)
	{
		response.sendRedirect("login.jsp");
	}

%>
	<div id="wrapper">
		<div id="header">
			<h2>SJSU</h2>
		
		</div>
	
	</div>
	
	
	<div id="container">
		<h3>Update Student</h3>
	
	<form action="StudentControllerServlet" method="Get">
	<input type="hidden" name="command" value="UPDATE" />
	
	<input type="hidden" name="studentId" value="${THE_STUDENT.id}" />
	
	<table> 
	<tbody>
	
	<tr>
	
	<td><label>First Name:</label></td>
	<td><input type="text" name="firstName" 
	value="${THE_STUDENT.firstName}"></td>
				
	</tr>
	
	<tr>

	<td><label>Last Name:</label></td>
	<td><input type="text" name="lastName" value="${THE_STUDENT.lastName}"></td>
	</tr>
	
	<tr>
	
	<td><label>Email:</label></td>
	<td><input type="text" name="email" value="${THE_STUDENT.email}"></td>
	</tr>
	
	<tr>
	
	<td><label>Phone:</label></td>
	<td><input type="text" name="phone" value="${THE_STUDENT.phone}"></td>
	</tr>

	<tr>
	<td><label>Phone:</label></td>
	<td><input type="text" name="phone" value="${THE_STUDENT.manager}"></td>
	</tr>


	<tr>
	
	<td><label></label></td>
	<td><input type="submit" value="Save" class="save"></td>
	</tr>
	
	
	
	
	
	
	
	</tbody>
	</table>
	
	</form>
	<div style="clear: both;"></div>
	<p>
		<a href="StudentControllerServlet">Back</a>
	</p>
	</div>




</body>


</html>