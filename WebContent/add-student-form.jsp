<!DOCTYPE html>
<html>
<head>
<title>Add Student</title>

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
		<h3>Add Student</h3>
	
	<form action="StudentControllerServlet" method="Get">
	<input type="hidden" name="command" value="ADD" />
	
	<table> 
	<tbody>
	
	<tr>
	
	<td><label>First Name:</label></td>
	<td><input type="text" name="firstName"></td>
	</tr>
	
	<tr>

	<td><label>Last Name:</label></td>
	<td><input type="text" name="lastName"></td>
	</tr>
	
	<tr>
	
	<td><label>Email:</label></td>
	<td><input type="text" name="email"></td>
	</tr>
	
	<tr>
	
	<td><label>Phone:</label></td>
	<td><input type="text" name="phone"></td>
	</tr>
	
	<tr>
	
	<td><label>Manager:</label></td>
	<td><input type="text" name="manager"></td>
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