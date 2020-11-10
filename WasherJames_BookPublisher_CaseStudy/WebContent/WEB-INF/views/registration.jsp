<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- jstl -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="<c:url value="/css/main.css" />" rel="stylesheet">
    <!-- This page is used when registering a new user account -->
</head>
<body>
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="./">
          Home
        </a>
        <a class="navbar-brand" href="./books">
          Books
        </a>
        <a class="navbar-brand" href="./cart">
          Cart
        </a>
        <a class="navbar-brand" href="./changepassword">
          Account
        </a>
    </nav>
	<h1>Sign Up</h1>
	<form action="register" method="POST">
		<div>
			<label>Email: </label>
			<input type="text" name="email" />
		</div>
		<div>
			<label>First Name: </label>
			<input type="text" name="fname" />
		</div>
		<div>
			<label>Last Name: </label>
			<input type="text" name="lname" />
		</div>
		<div>
			<label>Password: </label>
			<input type="password" name="password" />
		</div>
				<div>
			<label>Address: </label>
			<input type="text" name="address" />
		</div>
		<div>
			<label>Zip Code: </label>
			<input type="text" name="zip" />
		</div>
		<div>
			<input type="submit" value="Register" />
		</div>
	</form>
	<script src="<c:url value="/javascript/main.js" />"></script>
</body>
</html>