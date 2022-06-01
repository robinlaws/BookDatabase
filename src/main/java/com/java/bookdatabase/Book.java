package com.java.bookdatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Book object includes book title, book isbn, book edition number, and copyright year.
 * Author list of all authors of book.
 * @author Robin Laws
 * @version CP3566 Assignment 1
 */
public class Book {
    private final String title;
    private final String ISBN;
    private final int editionNumber;
    private final String copyright;

    private List<Author> authorList = new ArrayList<>();

    /**
     * Book constructor
     * @param ISBN book ISBN
     * @param title book title
     * @param editionNumber book edition number
     * @param copyright book copyright year
     */
    public Book(String ISBN, String title,  int editionNumber, String copyright){
        this.title = title;
        this.ISBN = ISBN;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
    }

    /**
     * method get author list to return books list of authors
     * @return author list
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * method add author list adds author to author list for book. Used when loading database.
     * @param author book author
     */
    public void addAuthorList(Author author) {
        this.authorList.add(author);
    }

    /**
     * method get title returns title of book
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * method get ISBN returns book ISBN
     * @return book ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * method get edition number returns book edition number
     * @return book edition number
     */
    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * method get copyright returns book copyright year
     * @return copyright
     */
    public String getCopyright() {
        return copyright;
    }
}

