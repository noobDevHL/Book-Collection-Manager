package com.example.bibliophilia;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * Data-Transfer-Objekt der Klasse Book
 *
 * @author Aline Hoffmann
 */
public class BookDTO {

    @NotEmpty
    @NotBlank
    private String title;

    @NotEmpty
    @NotBlank
    private String author;

    @NotEmpty
    @NotBlank
    private String isbn;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
