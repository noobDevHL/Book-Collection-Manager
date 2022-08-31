package com.example.bibliophilia;

import javax.validation.constraints.NotBlank;

/**
 * Data-Transfer-Objekt der Klasse Book
 *
 * @author Aline Hoffmann
 */
public class BookDto {

    private Long id;

    @NotBlank(message = "Titel darf nicht leer sein oder nur aus Leerzeichen bestehen")
    private String title;

    @NotBlank(message = "Autor darf nicht leer sein oder nur aus Leerzeichen bestehen")
    private String author;


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
