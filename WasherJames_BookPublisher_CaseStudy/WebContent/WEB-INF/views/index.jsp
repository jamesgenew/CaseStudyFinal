<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- jstl -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="<c:url value="/css/main.css" />" rel="stylesheet">
    <!-- This is the main index page, you cannot leave this page unless you are logged into an account. -->
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
        <a class="navbar-brand" href="./login">
          Login
        </a>
        <a class="navbar-brand" href="registration">
          Register
        </a>
        <a class="navbar-brand" href="./changepassword">
          Account
        </a>
    </nav>
    <h1 id="pubname">Washer Publishing</h1>
    <img id="bookimg" src="<c:url value="/images/books.jpg"/>"/>   
    
	<script src="<c:url value="/javascript/main.js" />"></script>  
</body>
</html>