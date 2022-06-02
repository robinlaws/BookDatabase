package com.java.bookdatabase;


import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class DBConnection {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/books";
    private final String USER = "root";
    private final String PASS = "36Empire";
    private HashMap<String, String> map = new HashMap<>();
    private List<Author> allAuthors = new ArrayList<>();
    private List<Book> allBooks = new ArrayList<>();
    public final static String TITLES_TABLE = "titles";
    public final static String TITLES_TITLE = "title";
    public final static String AUTHOR_TABLE = "authors";
    public final static String AUTHOR_ID = "authorID";
    public final static String AUTHOR_ISBN_TABLE = "authorISBN";
    public final static String ISBN = "ISBN";


    /**
     * Method prepare connection connects to database using pre-initialized values
     * in book database manager (driver, URL, user, password)
     * @return connection
     */
    public Connection prepareConnection() throws Exception{
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(
                DB_URL, USER, PASS);
        System.out.println("Connected to database.");
        return connection;
    }

    /**
     * Method load books loads all books from database and creates book objects
     * @throws SQLException sql exception
     */
    public void loadBooks() throws Exception {
        Connection connection = prepareConnection();
        Statement statement = connection.createStatement();
        allBooks.clear();
        ResultSet rs = statement.executeQuery("SELECT * FROM " + TITLES_TABLE);
        while (rs.next()) {
            Book book = new Book(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
            allBooks.add(book);
        }
        statement.close();
    }
    /**
     * Method load authors loads all authors from database and creates author objects
     *
     * @return
     * @throws SQLException sql exception
     */
    public void loadAuthors() throws Exception {
        Connection connection = prepareConnection();
        Statement statement = connection.createStatement();
        allAuthors.clear();
        ResultSet rs = statement.executeQuery("SELECT * FROM " + AUTHOR_TABLE);
        while (rs.next()) {
            Author author = new Author(rs.getInt(1), rs.getString(2), rs.getString(3));
            allAuthors.add(author);
        }
        statement.close();
    }
    /**
     * Method load database queries database tables using primary key relationships.
     * Books and authors are added to hash map with key: Book value: Authors
     * @throws SQLException sql exception
     */
    public void loadDatabase() throws Exception {
        Connection connection = prepareConnection();
        Statement statement = connection.createStatement();
        map.clear();
        String query = "SELECT " + TITLES_TABLE + "." + TITLES_TITLE + "," +
                AUTHOR_TABLE + "." + AUTHOR_ID +
                " FROM " + TITLES_TABLE +
                " JOIN " + AUTHOR_ISBN_TABLE +
                " ON " + TITLES_TABLE + "." + ISBN + "=" +
                AUTHOR_ISBN_TABLE + "." + ISBN +
                " JOIN " + AUTHOR_TABLE +
                " ON "  + AUTHOR_TABLE + "." + AUTHOR_ID + "=" +
                AUTHOR_ISBN_TABLE + "." + AUTHOR_ID;

        //sql query = SELECT titles.title, authors.authorID FROM titles
        //                  JOIN authorsISBN ON titles.isbn = authorsISBN.isbn
        //                  JOIN authors ON authors.authorID = authorISBN.authorID;

        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            String title = rs.getString(1);
            String authorID = rs.getString(2);
            if (map.containsKey(title)) {
                map.put(title, map.get(title) + ", " + authorID);
            } else {
                map.put(title, authorID);
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            for (Book book : allBooks) {
                if (entry.getKey().equals(book.getTitle())) {
                    for (Author author : allAuthors) {
                        if (entry.getValue().contains(Integer.toString(author.getAuthorID()))) {
                            book.addAuthorList(author);
                            author.addBookList(book);
                        }
                    }
                }
            }
        }
        statement.close();
    }

    /**
     * Method add new book allows the user to enter information to add book to database.
     */
    public void addNewBook(String ISBN, String copyright, String title, int editionNumber) throws Exception{
        Connection connection = prepareConnection();
        Statement statement = connection.createStatement();
        String query = "INSERT into " + TITLES_TABLE + " VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ISBN);
        preparedStatement.setString(2, title);
        preparedStatement.setInt(3, editionNumber);
        preparedStatement.setString(4, copyright);
        preparedStatement.executeUpdate();
        loadBooks();
        loadDatabase();
        System.out.println("Book added to database.");
        statement.close();

    }
    /**
     * Method add new author allows user to enter author information and add to database
     */
    public void addNewAuthor(String firstName, String lastName, String title) throws Exception{
        Connection connection = prepareConnection();
        Statement statement = connection.createStatement();
        String query = "INSERT into " + AUTHOR_TABLE +  " VALUES (DEFAULT, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.executeUpdate();
        loadAuthors();
        loadDatabase();
        System.out.println("Author added to database.");
        statement.close();
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public List<Author> getAllAuthors() {
        return allAuthors;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }
}
