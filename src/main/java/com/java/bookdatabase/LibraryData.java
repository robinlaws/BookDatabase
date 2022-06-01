package com.java.bookdatabase;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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

        try {
            dbConnection.prepareConnection();
            dbConnection.loadBooks();
            dbConnection.loadAuthors();
            dbConnection.loadDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewLibrary.jsp");
        request.setAttribute("bookList", dbConnection.getAllBooks());
        request.setAttribute("authorList", dbConnection.getAllAuthors());
        //TODO add the list to the request
        requestDispatcher.forward(request, response);
        //TODO add the list to the request
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String author = req.getParameter("author");
        String book = req.getParameter("book");
        String isbn = req.getParameter("isbn");
        int edition = parseInt(req.getParameter("edition"));
        String copyright = req.getParameter("copyright");
        try {
            dbConnection.addNewBook(isbn, copyright, book, edition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        doGet(req, resp);
        
        //TODO Here I want to handle the New Author and New Book form posts
    }
}