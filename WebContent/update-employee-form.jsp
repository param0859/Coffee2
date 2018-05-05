<!DOCTYPE html>
<html>

<head>

<title>Update Employee</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-employee-style.css">
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

	<div id="wrapper">

		<div class="row">
			<!-- Header -->
			<h2 id="heder">Cafe Mocha Employee Portal</h2>
		</div>

	</div>

	<div class="row" align="center">

		<h3 id="heder">Update Employee:</h3>

		<form action="EmployeeControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE" /> <input
				type="hidden" name="employeeId" value="${THE_EMPLOYEE.id}" />
				
<!-- Input fields for updating employee -->
			<table class="table">

				<tbody>

					<tr>
						<td><label>First name:</label></td>
						
						<td><input type="text" name="firstName"
							value="${THE_EMPLOYEE.firstName}" /></td>

					</tr>
					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName"
							value="${THE_EMPLOYEE.lastName}" class="form-control" /></td>

					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email"
							value="${THE_EMPLOYEE.email}" class="form-control" /></td>

					</tr>

					<tr>
						<td><label> Phone Number:</label></td>
						<td><input type="text" name="phone"
							value="${THE_EMPLOYEE.phone}" class="form-control" /></td>

					</tr>

					<tr>
						<td><label> Manager:</label></td>
						<td><input type="text" name="manager"
							value="${THE_EMPLOYEE.manager}" class="form-control" /></td>

					</tr>



					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save"
							class="form-control" /></td>

					</tr>

				</tbody>

			</table>


		</form>

		<div style="clear: both;"></div>

		<p>
<!-- Back button to go back to main view -->
			<button>
				<a href="EmployeeControllerServlet">Back</a>
			</button>
		</p>

	</div>

</body>

</html>

