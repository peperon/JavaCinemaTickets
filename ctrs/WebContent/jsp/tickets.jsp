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
<div>Ticket price ${initParam.price }</div>
<div style="color:red">${error_message }</div>
<form action="/ctrs/reserve_tickets" method="post">
<input type="hidden" name="movie_id" value="${movie_id }"/>
<fieldset style="width: 250px">
<table>
<c:forEach var="row_list" items="${main_list }">
	<tr>
		<c:forEach var="seat" items="${row_list }">
			<td>
				<c:choose>
					<c:when test="${seat.taken eq true}">
						<input type="checkbox" title="${seat.rowId }-${seat.columnId }" disabled="disabled" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input type="checkbox" title="${seat.rowId }-${seat.columnId }" name="seat" value="${seat.id }" />
					</c:otherwise>
				</c:choose>
			</td>
		</c:forEach>
	</tr>
</c:forEach>
</table>
</fieldset>

<c:if test="${sessionScope.user.userTypeId == 1 }">
	<input type="submit" value="Reserve tickets"/>
</c:if>
</form>
<c:if test="${not empty reserved_tickets }">
	<div><b>Reserved tickets:</b></div>
	<form action="/ctrs/confirm_tickets" method="post">
		<input type="hidden" name="movie_id" value="${movie_id }"/>
		<c:forEach  var="ticket" items="${reserved_tickets }" varStatus="current">
			<c:if test="${ticket.userId == sessionScope.user.id }">
				<div>Ticket with seat # ${ticket.seatId }<input type="checkbox" name="ticket" value="${ticket.seatId }"/></div>
			</c:if>
			<c:if test="${current.last }">
				
			</c:if>
		</c:forEach>
		<input type="submit" value="Confirm tickets"/>
	</form>
</c:if>
<div><b>Tickets:</b></div>
<c:choose>
	<c:when test="${sessionScope.user.userTypeId == 1 }">
		<c:forEach var="ticket" items="${movie_tickets }">
			<c:if test="${ticket.userId == sessionScope.user.id }">
				<div>Ticket for seat # ${ticket.seatId }</div>
			</c:if>
		</c:forEach>
	</c:when>
	<c:when test="${sessionScope.user.userTypeId > 1 }">
		<form action="/ctrs/check_tickets" method="post">
			<input type="hidden" name="movie_id" value="${movie_id }"/>
			<c:forEach var="ticket" items="${movie_tickets }" varStatus="current">
				<div>Ticket for seat # ${ticket.seatId } belonging to customer ${ticket.userName }
					<c:choose>
						<c:when test="${ticket.used }">
							<input type="checkbox" checked="checked" disabled="disabled" />
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="used" value="${ticket.id }"/>
						</c:otherwise>
					</c:choose>
				</div>
				<c:if test="${current.last }">
					<input type="submit" value="Save used tickets" />
				</c:if>
			</c:forEach>
		</form>
	</c:when>
</c:choose>
<div><a href="/ctrs/movies">Back to movies page</a></div>
<div><a href="/ctrs/logout">Logout</a></div>
</body>
</html>