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

/**
 * Webservlet class LibraryData provide book and author data depending on view requested.
 */
@WebServlet(name = "library", urlPatterns = "/library", description = "Book Library Servlet")
public class LibraryData extends HttpServlet {
    private String message;
    DBConnection dbConnection = new DBConnection();

    public void init() {
        message = "Library Servlet!";
    }

    /**
     * doGet Servlet method will display author or book data depending on view requested
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
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
        request.setAttribute("bookList", dbConnection.getAllBooks());
        request.setAttribute("authorList", dbConnection.getAllAuthors());
        if (Objects.equals(view, "books")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewBOOKS.jsp");
            requestDispatcher.forward(request, response);
        } if (Objects.equals(view, "author")){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewAUTHORS.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    /**
     * doPost Servlet method will process form data and add to library
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
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