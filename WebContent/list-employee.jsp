<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<!-- <meta http-equiv="refresh" content="5" /> -->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cafe Mocha App</title>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id"
	content="795524120590-t7k3ik0heqbvukhc1a5ffen77migrn7j.apps.googleusercontent.com">

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>


<body>

	<!-- <div id="wrapper">
		<div id="header">
			<h2>Cafe Mocha Employee Portal</h2>
		</div>
	</div>

	<div id="container">

		<div id="content"> -->


	<div class="row">
		<div class="col-sm-4" id="hed">CMPE 172</div>
		<div class="col-sm-4">
			<h4 align="center" id="cafe">Cafe Mocha Employee Portal</h4>
		</div>
		<div class="col-sm-4" id="hed">Group: Zero Gravity</div>
		<div align="right">
			<a class="btn btn-primary" id="g" href="https://www.gmail.com"
				target="_blank">Gmail</a>
		</div>
	</div>



<!-- Search Field -->
	<div class="row">

		<div class="col-md-6" align="left">
			<form action="EmployeeControllerServlet" method="GET">

				<input type="hidden" name="command" value="SEARCH" />
				<div class="g-signin2" data-onsuccess="onSignIn" id="myP"
					style="display: none;"></div>
				<div id="hecd">
					<font color="white">Search employee: </font><input type="text"
						name="theSearchName" />
				</div>

				<input type="submit" value="Search" class="add-employee-button" />

			</form>
		</div>


		<div class="col-md-6" align="right">
			<input type="button" value="Add Employee"
				onclick="window.location.href='add-employee-form.jsp'; return false;"
				class="add-employee-button" />
		</div>




	</div>

<!--  Table for listing employees -->
	<table class="table table-bordered">


		<thead>
			<tr>
				<th>First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">Email</th>
				<th scope="col">Phone Number</th>
				<th scope="col">Manager</th>
				<th scope="col">Action</th>
			</tr>
		</thead>


		<c:forEach var="tempEmployee" items="${EMPLOYEE_LIST}">


			<c:url var="tempLink" value="EmployeeControllerServlet">
				<c:param name="command" value="LOAD" />
				<c:param name="employeeId" value="${tempEmployee.id}" />
			</c:url>


			<c:url var="deleteLink" value="EmployeeControllerServlet">
				<c:param name="command" value="DELETE" />
				<c:param name="employeeId" value="${tempEmployee.id}" />
			</c:url>

			<tbody>
				<tr>
					<td>${tempEmployee.firstName}</td>
					<td>${tempEmployee.lastName}</td>
					<td>${tempEmployee.email}</td>
					<td>${tempEmployee.phone}</td>
					<td>${tempEmployee.manager}</td>
					<td>
						<div>

							<button type="button" class="btn btn-success">
								<a id="update" href="${tempLink}">Update</a>
							</button>
							<button type="button" class="btn btn-danger">
								<a id="delete" href="${deleteLink}"
									onclick="if(!(confirm('Are you sure you would like to delete this employee?'))) return false">Delete</a>
							</button>


						</div>
					</td>


				</tr>
		</c:forEach>

		</tbody>
	</table>









	<!-- 	<form action="Logout">
	<input type="submit" value="Logout">
	
	
	</form> -->

<!--Sign out button  -->
	<button onclick="myFunction()">Sign Out</button>
	<script>
      function myFunction() {
    	  gapi.auth2.getAuthInstance().disconnect();
          location.reload();
    	//that.myFunction();  
      location.href = "index.jsp";
   }
   </script>
</body>


</html>








