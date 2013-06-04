<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
<%@ include file="/css/default.css"%>
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>
<div style="color:green">${system_message }</div>
<div style="color:red">${error_message }</div>
<form action="/ctrs/login" method="post">
	<fieldset>
		<legend>Login</legend>
		<p><label for="user_name">Username:</label><input type="text" name="user_name"/></p>
		<p><label for="password">Password:</label><input type="password" name="password"/></p>
		<div></div>
		<input type="submit" value="Log in"/>
		<a href="/ctrs/register"><button type="button">Register</button></a> 
	</fieldset>
</form>
</body>
</html>