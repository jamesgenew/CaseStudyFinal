<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- jstl -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="<c:url value="/css/main.css" />" rel="stylesheet">
    <!-- books page will display all books on sale by the publisher -->
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
	<h1>Books:</h1> 
		<table>
		<c:forEach var="currentBook" items = "${theBooks}"> <!-- loop through theBooks list of Book passed by controller -->
			<tr>		
					<td><img src="<c:url value="/images/${currentBook.getCoverArt()}"/>"/></td> 
					<td><h3>${currentBook.getBookTitle()}</h2></td>
					<td><h3>$${currentBook.getBookPrice()}</h2></td>
					<td><h3>${currentBook.getGenre()}</h2></td>
					<td><h3><a href = "displayauthor/${currentBook.getId()}">${currentBook.getAuthor()}</a></h2></td> 
					<td>
						<form action="addtocart/${currentBook.getId()}" method="POST">
							<input type="submit" value="Add to Cart" name = "${currentBook.getId()}"/>	
						</form>
					</td>
			</tr>
		</c:forEach>
		</table>
	<script src="<c:url value="/javascript/main.js" />"></script>
</body>
</html>