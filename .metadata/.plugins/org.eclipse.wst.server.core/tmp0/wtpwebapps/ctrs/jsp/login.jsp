<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>
<div style="color:green">${system_message }</div>
<div style="color:red">${error_message }</div>
<form action="/ctrs/login" method="post">
	<input type="text" name="user_name"/>
	<input type="password" name="password"/>
	<input type="submit" value="Log in"/>
</form>
</body>
</html>