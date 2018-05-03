<!DOCTYPE html>
<html>

<head>

<title>Add Employee</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-employee-style.css">

<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>

</head>

<body>

<div id="wrapper">

<div class="row">

<h2>Cafe Mocha Employee Portal</h2>
</div>

</div>

<div class="row" align="center">

<h3>Add Employee:</h3>

 <form action="EmployeeControllerServlet" method="GET" name="myForm" class="form-group">

<input type="hidden" name="command" value="ADD"/>

<table>

<tbody>

<tr>
<td><label>First name:</label></td>
<td><input type="text" name="firstName" class="form-control" id="first_name"/></td>

</tr>
<tr>
<td><label>Last name:</label></td>
<td><input type="text" name="lastName" id="last_name" class="form-control"/></td>

</tr>

<tr>
<td><label>Email:</label></td>
<td><input type="text" name="email" id="email" class="form-control"/></td>

</tr>

<tr>
<td><label>Phone:</label></td>
<td><input type="text" name="phone" id="phone" class="form-control"/></td>

</tr>

<tr>
<td><label>Manager:</label></td>
<td><input type="text" name="manager" id="manager" class="form-control"/></td>

</tr>

<tr>
<td><label></label></td>
<td><input type="submit" value="Save"  class="save" onClick="validate()"/></td>

</tr>

</tbody>

</table>


</form>

 
 
<div style = "clear: both;"></div>

<p>

<a id="back" href="EmployeeControllerServlet">Back</a>
</p>

</div>

</body>

<script type="text/javascript">
    function validate() {
        var firstName = myForm.first_name.value;
        var lastName = myForm.last_name.value;
        var email = myForm.email.value;
		var phone = myForm.phone.value;
		var manager = myForm.manager.value;
		
        if (firstName == null || firstName.trim() == "") {
            alert('Please enter first name.');
            myForm.first_name.focus();
            return false; // cancel submission
        }
        else if(lastName == null || lastName.trim() == ""){
         alert('Please enter last name.');
             myForm.last_name.focus();
             return false; // cancel submission
        }
else if(email == null || email.trim() == ""){
	        alert('Please enter manager');
	            myForm.email.focus();
	            return false; // cancel submission
	       }
else if(phone == null || phone.trim() == ""){
	        alert('Please enter phone');
	            myForm.phone.focus();
	            return false; // cancel submission
	       }

else if(manager == null || email.trim() == ""){
	        alert('Please enter manager');
	            myForm.manager.focus();
	            return false; // cancel submission
	       }
else if(isNoN(phone))
	{
	        alert('Please enter correct phone');
	            myForm.phone.focus();
	            return false; // cancel submission
	}


        else {
            document.myForm.submit(); // allow submit
        }
    }
</script>

</html>



