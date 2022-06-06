<%@ page import="com.java.bookdatabase.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.java.bookdatabase.Author" %>
<%--
  Created by IntelliJ IDEA.
  User: robinlaws
  Date: 2022-05-31
  Time: 7:38 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="styles/style.css"/>
<html>
<head>
    <title>Book database - Library</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<% List<Book> bookList = (List<Book>) request.getAttribute("bookList"); %>
<% List<Author> authorList = (List<Author>) request.getAttribute("authorList"); %>

<div class="table">
<h2>LIBRARY BY BOOKS</h2>
<table>
    <tr>
        <th>ISBN</th>
        <th>Title</th>
        <th>Edition</th>
        <th>Copyright</th>
        <th>Authors</th>
        <th></th><th></th><th></th>

    </tr>
    <% for (Book book : bookList) { %>
    <tr>
        <td><%=book.getISBN()%></td>
        <td><%=book.getTitle()%></td>
        <td><%=book.getEditionNumber()%></td>
        <td><%=book.getCopyright()%>
        <% for (Author author : book.getAuthorList()) { %>
            <td><%=author.getFullName()%></td>
        <% } %>
    </tr>
    <% } %>
</table>
</div>

</body>
</html>

