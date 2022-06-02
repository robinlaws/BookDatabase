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
<div class="container">
<h1>ADD AN AUTHOR</h1>
<form action = "library" method = "POST" name="">
    <label>Author First Name: <input type = "text" name = "firstName" required> </label>
    <br />
    <label>Author Last Name: <input type = "text" name = "lastName" required> </label>
    <br />
    <label>Book: <input type = "text" name = "title" required /> </label>
    <label>ISBN: <input type = "text" name = "isbn" required/> </label>
    <input type="hidden" name="formType" value="addAuthor">
    <input  type = "submit" value = "Submit" onsubmit='alert("Author submitted to database!")'/>
</form>
</div>
</body>
</html>
