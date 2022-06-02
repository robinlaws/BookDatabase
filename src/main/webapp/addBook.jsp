<%--
  Created by IntelliJ IDEA.
  User: robinlaws
  Date: 2022-05-30
  Time: 6:20 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book database - add book</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<div class="container">
<h1>ADD A BOOK</h1>
<form action = "library" method = "POST">
    <label>Book Title:<input type = "text" name = "book" required> </label>
    <label>Book ISBN: <input type = "text" name = "isbn" required ></label>
    <label>Book Edition: <input type = "text" name = "edition" pattern="[0-9]" required></label>
    <label>Book Copyright: <input type = "text" name = "copyright" maxlength="4" required></label>
    <label>Author First Name: <input type = "text" name = "firstName" required/></label>
    <label>Author Last Name: <input type = "text" name = "lastName" required/></label>
    <input type="hidden" name="formType" value="addBook">
    <br />
    <input type = "submit" value = "Submit" onsubmit="alert('Book successfully added to database!')"/>
</form>
</div>
</body>
</html>
