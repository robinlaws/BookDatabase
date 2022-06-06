<%@ page import="com.java.bookdatabase.Author" %>
<%@ page import="com.java.bookdatabase.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: robinlaws
  Date: 2022-06-06
  Time: 10:35 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="navbar.jsp" />

<% List<Book> bookList = (List<Book>) request.getAttribute("bookList"); %>
<% List<Author> authorList = (List<Author>) request.getAttribute("authorList"); %>

<div class="table">
  <h2>LIBRARY BY AUTHORS</h2>
  <table>
    <tr>
      <th>Author ID</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Books</th>
      <th/><th/><th/><th/><th/><th/><th/><th/><th/><th/><th/>
    </tr>

    <% for (Author author : authorList) { %>
    <tr>
      <td><%=author.getAuthorID()%></td>
      <td><%=author.getFirstName()%></td>
      <td><%=author.getLastName()%></td>
      <% for (Book book : author.getBookList()) { %>
      <td><%=book.getTitle()%></td>
      <% } %>
    </tr>
    <% } %>
  </table>
</div>

</body>
</html>
