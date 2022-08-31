package com.example.bibliophilia;

import org.hibernate.validator.internal.util.Contracts;


import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Diese Klasse repraesentiert einen Fachwert der ISBN eines Buches
 *
 * @author Aline Hoffmann
 */
public class dvIsbnNumber {


    // @Column(unique=true, name = "isbn")
    private String _isbn;


    private dvIsbnNumber(String isbn) {
        Contracts.assertTrue(isValid(isbn), "Die ISBN muss dem Muster 978-x-xxx-xxxxx-x entsprechen.");
        _isbn = isbn;
    }

    public static dvIsbnNumber valueOf(String isbn) {
        Contracts.assertTrue(isValid(isbn), "Die ISBN muss dem Muster 978-x-xxx-xxxxx-x entsprechen.");
        return new dvIsbnNumber(isbn);
    }

    public static boolean isValid(String isbn) {
        Pattern p = Pattern.compile("(978-)\\d-\\d{3}-\\d{5}-\\d");
        Matcher m = p.matcher(isbn);
        return m.matches();
    }

    public String getIsbn() {
        return _isbn;
    }

    @Override
    public String toString() {
        return _isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dvIsbnNumber that = (dvIsbnNumber) o;
        return Objects.equals(_isbn, that._isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_isbn);
    }
}
