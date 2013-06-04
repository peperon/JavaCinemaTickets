<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home screen</title>
</head>
<body>
<c:choose>
	<c:when test="${sessionScope.user != null}">
		<span>Welcome ${sessionScope.user.userName }!</span>
		<div><a href="/ctrs/movies">Movies</a></div>
		<c:if test="${sessionScope.user.userTypeId != 1}">
		<div><a href="/ctrs/users">Users</a></div>
		</c:if>
		<div><a href="/ctrs/logout">Logout</a></div>		
		
	</c:when>
	<c:otherwise>
		<c:redirect url="/ctrs/login"/>
	</c:otherwise>
</c:choose>
</body>
</html>