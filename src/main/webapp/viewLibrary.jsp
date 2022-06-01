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

<h2>LIBRARY BY BOOKS</h2>
<table>
    <tr>
        <th>ISBN</th>
        <th>Title</th>
        <th>Edition</th>
        <th>Copyright</th>
        <th>Authors</th>
    </tr>

    <% for (Book book : bookList) { %>
    <tr>
        <td><%=book.getISBN()%>
        </td>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getEditionNumber()%>
        </td>
        <td><%=book.getCopyright()%>
        <% for (Author author : book.getAuthorList()) { %>
            <td>&#8226<%=author.getFullName()%>&#8226</td>
        <% } %>
    </tr>
    <% } %>
</table>

<h2>LIBRARY BY AUTHORS</h2>
<table>
    <tr>
        <th>Author ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Books</th>
    </tr>

    <% for (Author author : authorList) { %>
    <tr>
        <td><%=author.getAuthorID()%>
        </td>
        <td><%=author.getFirstName()%>
        </td>
        <td><%=author.getLastName()%>
        </td>
        <% for (Book book : author.getBookList()) { %>
            <td>&#8226<%=book.getTitle()%>&#8226</td>
        <% } %>

    </tr>
    <% } %>
</table>

</body>
</html>
