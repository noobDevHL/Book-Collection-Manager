package com.example.bibliophilia;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Repräsentation eines Buchs mit Titel, Autor und ISBN-Nummer
 *
 * @author Aline Hoffmann
 */

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id; // wird durch Annotationen automatisch erzeugt

    private String _title;
    private String _author;
    private String _isbn;

    public Book() {
        // required by Hibernate
    }

    public Book(String title, String author, String isbn) {
        this._title = title;
        this._author = author;
        this._isbn = isbn;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String author) {
        this._author = author;
    }

    public String getIsbn() {
        return _isbn;
    }

    public void setIsbn(String isbn) {
        this._isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
