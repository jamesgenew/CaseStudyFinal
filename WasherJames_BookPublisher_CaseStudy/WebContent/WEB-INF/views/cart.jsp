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
    <!-- this page will display the books in the user's cart and the subtotal of the books in the cart-->
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
	<h1>Your Cart:</h1>
	<table>
		<c:forEach var="currentBook" items = "${theBooks}"> <!-- loop through theBooks list of Book passed by controller -->
			<tr>
					<td><img src="<c:url value="/images/${currentBook.getCoverArt()}"/>"/></td>
					<td><h2>${currentBook.getBookTitle()}</h2></td>
					<td><h2>$${currentBook.getBookPrice()}</h2></td>
					<td><h2>${currentBook.getGenre()}</h2></td>
					<td><h2>${currentBook.getAuthor()}</h2></td>
					<td>
						<form action="deletefromcart/${currentBook.getId()}" method="POST">
							<input type="submit" value="Remove" name = "${currentBook.getId()}"/>	
						</form>
					</td>
			</tr>
		</c:forEach>
	</table>
	<h1>Your subtotal is: $${subtotal}</h1> 
	<form action="checkout" method="POST">
		<input type="submit" value="Purchase"/>	
	</form>
	<script src="<c:url value="/javascript/main.js" />"></script>
</body>
</html>