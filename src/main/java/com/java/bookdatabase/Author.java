package com.java.bookdatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Author includes author first name, last name, and author ID (auto increment from database).
 * Book list of all books author has written.
 * @author Robin Laws
 * @version CP3566 Assignment 1
 */
public class Author {
    private final int authorID;
    private final String firstName;
    private final String lastName;
    private List<Book> bookList = new ArrayList<>();

    /**
     * Author constructor
     * @param authorID author ID
     * @param firstName author first name
     * @param lastName author last name
     */
    public Author(int authorID, String firstName, String lastName){
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * method add book list adds book to list of books by author
     * @param book book
     */
    public void addBookList(Book book) {
        this.bookList.add(book);
    }

    /**
     * method get author ID returns author ID (auto increment from database)
     * @return authorID
     */
    public int getAuthorID() {
        return authorID;
    }

    /**
     * method get first name returns author first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * method get last name returns author last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * method get book list returns list of books by author
     * @return book list
     */
    public List<Book> getBookList() {
        return bookList;
    }

    public String getFullName() { return firstName + ", " + lastName;}
}
