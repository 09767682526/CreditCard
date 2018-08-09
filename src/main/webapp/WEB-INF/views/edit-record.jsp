<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>

<h1>Edit Existing Record</h1>

<%-- <form method="POST" action="edit?id=${currentPerson.id}"> --%>
<form method="POST" action="edit">
	<table>
		<tr>
			<td>Id:</td>
			<td><input type="text" name="id"  value="${currentPerson.id}" disabled="true"/></td>
		</tr>
	
		<tr>
			<td>First Name:</td>
			<td><input type="text" value="${currentPerson.firstName}" name="firstName"/></td>
		</tr>

		<tr>
			<td>Last Name</td>
			<td><input type="text" value="${currentPerson.lastName}" name="lastName"/></td>
		</tr>
		
		<tr>
			<td>Money</td>
			<td><input type="text" value="${currentPerson.money}" name="money"/></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" value="Edit" />
			</td>
		</tr>
	</table>
	
	<input type="hidden" name="id"  value="${currentPerson.id}" /> 
	
</form>

</body>
</html>