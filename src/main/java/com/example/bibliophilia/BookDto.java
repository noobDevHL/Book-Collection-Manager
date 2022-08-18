package com.example.bibliophilia;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Data-Transfer-Objekt der Klasse Book
 *
 * @author Aline Hoffmann
 */
public class BookDto {

    private Long id;

    @NotEmpty
    @NotBlank
    @NotNull
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
