<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tickets</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<td>Movie</td>
				<td>Owner</td>
				<td>Seat</td>
				<td>Date</td>
				<td>Used</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="ticket" items="${user_tickets }">
				<tr>
					<td>${ticket.movie.title }</td>
					<td>${ticket.user.userName }</td>
					<td>Row: ${ticket.seat.row } Seat: ${ticket.seat.column }</td>
					<td>${ticket.date }</td>
					<td>${ticket.used }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div><a href="/ctrs/users">Back to users page</a></div>
	<div><a href="/ctrs/logout">Logout</a></div>
</body>
</html>