<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tickets page</title>
</head>
<body>
<form action="/ctrs/tickets" method="post">
<input type="hidden" name="movie_id" value="${movie_id }"/>
<table>
<c:forEach var="row_list" items="${main_list }">
	<tr>
		<c:forEach var="seat" items="${row_list }">
			<td>
				<c:choose>
					<c:when test="${seat.taken eq true}">
						<input type="checkbox" disabled="disabled" checked="checked"/>
					</c:when>		
					<c:otherwise>
						<input type="checkbox" name="seat" value="${seat.id }" />
					</c:otherwise>
				</c:choose>
			</td>
		</c:forEach>
	</tr>
</c:forEach>
</table>
<input type="submit" value="Reserve tickets"/>
</form>
<div>Reserved tickets:</div>
<c:forEach var="ticket" items="${reserved_tickets }">
	<div>Ticket with seat ${ticket.seatId }</div>
</c:forEach>
</body>
</html>