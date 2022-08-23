package com.example.bibliophilia;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data-Transfer-Objekt der Klasse Book
 *
 * @author Aline Hoffmann
 */
public class BookDto {

    private Long id;

    @NotEmpty(message = "Titel muss angegeben werden")
    @NotBlank(message = "Titel darf nicht nur aus Leerzeichen bestehen")
    private String title;

    @NotEmpty(message = "Autor muss angegeben werden")
    @NotBlank(message = "Titel darf nicht nur aus Leerzeichen bestehen")
    private String author;

    @NotEmpty(message = "ISBN muss angegeben werden")
    @NotBlank(message = "ISBN darf nicht nur aus Leerzeichen bestehen")
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
