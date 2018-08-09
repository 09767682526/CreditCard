<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>

<h1>Edit Existing Credit Card</h1>

<form method="POST" action="edit?id=${existingCreditCard.id}">
	
	<table>	
		<tr>
			<td>Person Id:</td>
			<td><input type="text" value="${personId}" disabled="true"/>
		</tr>
		
		<tr>
			<td>Type:</td>
			<td><input type="text" name="type" value="${existingCreditCard.type}" /></td>
		</tr>

		<tr>
			<td>Number:</td>
			<td><input type="text" name="number" value="${existingCreditCard.number}" /></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" value="Edit" />
			</td>		
		</tr>
	</table>	
	
</form>

</body>
</html>