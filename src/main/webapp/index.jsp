<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JAVA BOOK DATABASE</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<div class="container">
<h1>Welcome to the Book Database</h1>
<img src="styles/images/books.jpeg"/>
<h2>What would you like to do?</h2>
<div class="list">
<li><a href="library", value="/library">VIEW BOOK LIBRARY</a><br/></li>
        <li><a href="addAuthor.jsp", value="/add-author">ADD AUTHOR TO LIBRARY</a><br/></li>
        <li><a href="addBook.jsp", value="/add-book">ADD BOOK TO DATABASE</a><br/></li>
</div>

</div>
</body>
</html>