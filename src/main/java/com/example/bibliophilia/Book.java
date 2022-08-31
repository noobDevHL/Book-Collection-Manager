package com.example.bibliophilia;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Repräsentation des Materials Buch mit Titel, Autor und ISBN
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

    private dvIsbnNumber _isbn;

    public Book() {
        // required by Hibernate
    }

    public Book(String title, String author, dvIsbnNumber isbn) {
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

    public dvIsbnNumber getIsbn() {
        return _isbn;
    }

    public void setIsbn(dvIsbnNumber isbn) {
        this._isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
