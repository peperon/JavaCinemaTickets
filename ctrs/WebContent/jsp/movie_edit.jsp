<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit movie</title>
</head>
<body>
<div style="color:green">${system_message }</div>
<div style="color:red">${error_message }</div>
<c:choose>
<c:when test="${sessionScope.user.userTypeId != 1 }">
	<form method="post" action="/ctrs/movie/">
		<input type="hidden" name="id" value="${movie.id }"/>
		<div>Title<input type="text" name="title" value="${movie.title }"/></div>
		<div>Hall
			<select name="hall">
				<option value="-">-</option>
				<c:forEach var="hall" items="${halls }">
					<option value="${hall.id }" <c:if test="${hall.id == movie.hallId}">selected</c:if>>${hall.name }</option>
				</c:forEach>
			</select>
		</div>
		<div>Year<input type="text" name="year" value="${movie.year }"/></div>
		<div>Description<textarea name="description" rows="5" cols="50">${movie.description }</textarea></div>
		<div>Length<input type="text" name="length" value="${movie.length }"/></div>
		<div>Projection date<input type="text" name="projection_date" 
								   value="<fmt:formatDate pattern="dd.MM.yyyy" value="${movie.projection }"/>"/></div>
		<div>Projection time<input type="text" name="projection_time" 
								   value="<fmt:formatDate pattern="HH:mm" value="${movie.projection }"/>"/></div>
		<input type="submit" value="Save"/>
	</form>
</c:when>
<c:otherwise>
	<div>Title: ${movie.title }</div>
	<div>Hall: <c:forEach var="hall" items="${halls }">
				<c:if test="${hall.id == movie.hallId }">${hall.name }</c:if>
				</c:forEach>
	</div>
	<div>Year: ${movie.year}</div>
	<div>Description: <textarea rows="5" cols="50">${movie.description }</textarea></div>
	<div>Projection date: <fmt:formatDate pattern="dd.MM.yyyy" value="${movie.projection }"/></div>
	<div>Projection time: <fmt:formatDate pattern="HH:mm" value="${movie.projection }"/></div>
</c:otherwise>
</c:choose>
<div><a href="/ctrs/movies">Back to movie list</a></div>
<div><a href="/ctrs/logout">Logout</a></div>
</body>
</html>