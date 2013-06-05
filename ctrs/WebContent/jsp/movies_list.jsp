<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="utils.UserTypes" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of movies</title>
</head>
<body>
<table border="1">
	<thead>
		<tr>
			<td>Title</td>
			<td>Hall</td>
			<td>Year</td>
			<td>Length</td>
			<td>Projection</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="movie" items="${movies }">
			<tr>
				<td>${movie.title }</td>
				<td>${movie.hallName }</td>
				<td>${movie.year }</td>
				<td>${movie.length }</td>
				<td>${movie.projection }</td>
				<td><a href="/ctrs/movie?id=${movie.id }">
					<c:choose>
						<c:when test="${sessionScope.user != null && sessionScope.user.userTypeId != 1 }">
							Edit
						</c:when>
						<c:otherwise>
							View
						</c:otherwise>
					</c:choose></a></td>
				<td><a href="/ctrs/tickets?movie_id=${movie.id }">Tickets</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:if test="${sessionScope.user != null && sessionScope.user.userTypeId != 1}">
	<div><a href="/ctrs/movie">Add new movie</a></div>
</c:if>
<div><a href="/ctrs/home">Back to welcome page</a></div>
<div><a href="/ctrs/logout">Logout</a></div>
</body>
</html>