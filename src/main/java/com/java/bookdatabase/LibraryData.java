package com.java.bookdatabase;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


import static java.lang.Integer.parseInt;

@WebServlet(name = "library", urlPatterns = "/library", description = "Book Library Servlet")
public class LibraryData extends HttpServlet {
    private String message;
    DBConnection dbConnection = new DBConnection();

    public void init() {
        message = "Library Servlet!";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String view = request.getParameter("view");

        try {
            dbConnection.loadBooks();
            dbConnection.loadAuthors();
            dbConnection.loadDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (Objects.equals(view, "books")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewBOOKS.jsp");
            request.setAttribute("bookList", dbConnection.getAllBooks());
            request.setAttribute("authorList", dbConnection.getAllAuthors());
            requestDispatcher.forward(request, response);
        } if (Objects.equals(view, "author")){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewAUTHORS.jsp");
            request.setAttribute("bookList", dbConnection.getAllBooks());
            request.setAttribute("authorList", dbConnection.getAllAuthors());
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String formType = req.getParameter("formType");
        if (formType.equals("addBook")) {
            String book = req.getParameter("book");
            String isbn = req.getParameter("isbn");
            int edition = parseInt(req.getParameter("edition"));
            String copyright = req.getParameter("copyright");
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            try {
                dbConnection.addNewBook(isbn, copyright, book, edition);
                dbConnection.addNewAuthor(firstName, lastName);
                dbConnection.addAuthorISBN(isbn, firstName, lastName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (formType.equals("addAuthor")) {
            String title = req.getParameter("title");
            String isbn = req.getParameter("isbn");
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            try {
                dbConnection.addNewBook(isbn, "NA", title, 0);
                dbConnection.addNewAuthor(firstName, lastName);
                dbConnection.addAuthorISBN(isbn, firstName, lastName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("submitted.jsp");
        requestDispatcher.forward(req, resp);
    }
}