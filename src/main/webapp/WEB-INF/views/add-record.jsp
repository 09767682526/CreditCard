<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<body>

<h1>Create New Record</h1>

<form method="POST" action="add">
	<table>
		<tr>
			<td>First Name:</td>
			<td><input type="text" name="firstName"/></td>
		</tr>

		<tr>
			<td>Last Name</td>
			<td><input type="text" name="lastName"/></td>
		</tr>
		
		<tr>
			<td>Money</td>
			<td><input type="text" name="money"/></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" value="Save" />
			</td>
		</tr>
	</table>	
	
</form>

</body>
</html>