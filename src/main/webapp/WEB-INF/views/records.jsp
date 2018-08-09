<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1>Records</h1>

<table style="border: 1px solid; width: 100%; text-align:center">
	
		<tr style="background:#d3dce3">
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Money</th>
			<th colspan="2"></th>
			<th>CC Type</th>
			<th>CC Number</th>
			<th colspan="3"></th>
		</tr>
	
	<c:forEach items="${persons}" var="person">
				
		<c:if test="${!empty person.creditCards}">
			<c:forEach items="${person.creditCards}" var="creditCard">
			<tr style="background:#ccc">
				<td><c:out value="${person.id}" /></td>
				<td><c:out value="${person.firstName}" /></td>
				<td><c:out value="${person.lastName}" /></td>
				<td><c:out value="${person.money}" /></td>
				<td><a href="editRecord?id=${person.id}"><img src="/resources/img/edit.png"></img></a></td>
				<td><a href="delete?id=${person.id}"><img src="/resources/img/delete.png"></img></a></td>
				
				<td><c:out value="${creditCard.type}" /></td>
				<td><c:out value="${creditCard.number}" /></td>
				<td><a href="creditcard/addCreditCard?id=${person.id}">+</a></td>
				<td><a href="creditcard/editCreditCard?pid=${person.id}&cid=${creditCard.id}"><img src="/resources/img/edit.png"></img></a></td>
				<td><a href="creditcard/delete?id=${creditCard.id}"><img src="/resources/img/delete.png"></img></a></td>
			</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${empty person.creditCards}">
			<tr style="background:#ccc">
				<td><c:out value="${person.id}" /></td>
				<td><c:out value="${person.firstName}" /></td>
				<td><c:out value="${person.lastName}" /></td>
				<td><c:out value="${person.money}" /></td>
				<td><a href="editRecord?id=${person.id}"><img src="/resources/img/edit.png"></img></a></td>
				<td><a href="delete?id=${person.id}"><img src="/resources/img/delete.png"></img></a></td>
				
				<td>N/A</td>
				<td>N/A</td>				
				<td><a href="creditcard/addCreditCard?id=${person.id}">+</a></td>
				<td></td>
				<td></td>
			</tr>
		</c:if>
		
	</c:forEach>
	</tbody>
</table>

<c:if test="${empty persons}">
	No records found. 
</c:if>

<p><a href="addNewRecord">Create new record</a></p>

</body>
</html>