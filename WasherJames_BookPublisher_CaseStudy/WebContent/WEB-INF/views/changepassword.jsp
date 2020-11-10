<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="<c:url value="/css/main.css" />" rel="stylesheet">
    <!-- This page will display the user's account, allowing them to change their password, access their books and log out -->
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
	<h1>Change Password</h1>
	<form action="newpassword" method="POST">
		<div>
			<label>Enter old Password: </label>
			<input type="password" name="oldpassword" />
		</div>
		<div>
			<label>Enter new Password: </label>
			<input type="password" name="password" />
		</div>
		<div>
			<input type="submit" value="submit" />
		</div>
	</form>
	<br>
	<a href="./ownedbooks" class="btn btn-secondary">My Books</a><br>
	<br>
	<a href="./logout" class="btn btn-secondary">Log Out</a>
	<script src="<c:url value="/javascript/main.js" />"></script>
</body>
</html>