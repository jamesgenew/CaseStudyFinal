<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- jstl -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Books</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="<c:url value="/css/main.css" />" rel="stylesheet">
    <!-- This page shows a user's owned books -->
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
	<h1>Your Books</h1>
	<table>
	<c:forEach var="currentBook" items = "${aBooks}">
		<tr>		
				<td><img src="<c:url value="/images/${currentBook.getCoverArt()}"/>"/></td>
				<td><h2>${currentBook.getBookTitle()}</h2></td>
				<td><h2>${currentBook.getGenre()}</h2></td>
				<td><h2><a href = "displayauthor/${currentBook.getId()}">${currentBook.getAuthor()}</a></h2></td> 
		</tr>
	</c:forEach>
	</table>
	<script src="<c:url value="/javascript/main.js" />"></script>
</body>
</html>