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
    <title>Book database - add author</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<h1>ADD AN AUTHOR</h1>
<form action = "library" method = "PUT">
    <label>Author First Name: <input type = "text" name = "firstname"> </label>
    <br />
    <label>Author Last Name: <input type = "text" name = "lastname"> </label>
    <br />
    <label>Book: <input type = "text" name = "title" /> </label>
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>