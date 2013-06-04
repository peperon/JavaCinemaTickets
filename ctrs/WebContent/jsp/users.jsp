<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<td>User name</td>
				<td>First name</td>
				<td>Last name</td>
				<td>Tickets</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users }">
				<tr>
					<td>${user.userName }</td>
					<td>${user.firstName }</td>
					<td>${user.lastName }</td>
					<td><a href="/ctrs/user_tickets?user_id=${user.id }">${user.tickets.size() }</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div><a href="/ctrs/home">Back to welcome page</a></div>
	<div><a href="/ctrs/logout">Logout</a></div>
</body>
</html>