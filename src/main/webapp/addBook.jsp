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
<h1>ADD A BOOK</h1>
<form action = "library" method = "PUT">
    <label>Book Title:<input type = "text" name = "book"> </label>
    <label>Book ISBN: <input type = "text" name = "isbn"></label>
    <label>Book Edition: <input type = "text" name = "edition"></label>
    <label>Book Copyright: <input type = "text" name = "copyright"></label>
    <label>Author(s): <input type = "text" name = "author" /></label>
    <br />
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>
