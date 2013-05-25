<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/ctrs/WebContent/js/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register page</title>
</head>
<body>
	<span style="color:red">${error_message }</span>
	<form action="/ctrs/register" method="post">
		<div>
			<label for="username">Username:</label>
			<input type="text" name="user_name"/>
		</div>
		<div>
			<label for="password">Password:</label>
			<input type="password" name="password"/>
		</div>
		<div>
			<label for="password2">Repeat password:</label>
			<input type="password" name="password2"/>
		</div>
		<div>
			<label for="first_name">First name:</label>
			<input type="text" name="first_name"/>
		</div>
		<div>
			<label for="last_name">Last name:</label>
			<input type="text" name="last_name"/>
		</div>
		<input type="submit" value="Register"/>
	</form>
</body>
</html>