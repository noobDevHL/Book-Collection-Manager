package com.example.bibliophilia;

import org.hibernate.validator.internal.util.Contracts;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Diese Klasse repraesentiert einen Fachwert der ISBN eines Buches
 *
 * @author Aline Hoffmann
 */
@Embeddable
public class dvIsbnNumber implements Serializable {

    @NotEmpty(message = "ISBN muss angegeben werden")
    @NotBlank(message = "ISBN darf nicht nur aus Leerzeichen bestehen")
    // @Pattern(regexp = "(978-3-)\\d{3}-\\d{5}-\\d", message = "Die ISBN muss dem Muster 978-3-xxx-xxxxx-x entsprechen.")
    private String _isbn;

    public dvIsbnNumber() {
    }

    private dvIsbnNumber(String isbn) {
        Contracts.assertTrue(isValid(isbn), "Die ISBN muss dem Muster 978-3-xxx-xxxxx-x entsprechen.");
        _isbn = isbn;
    }

    public static dvIsbnNumber valueOf(String isbn) {
        Contracts.assertTrue(isValid(isbn), "Die ISBN muss dem Muster 978-3-xxx-xxxxx-x entsprechen.");
        return new dvIsbnNumber(isbn);
    }

    public static boolean isValid(String isbn) {
        Pattern p = Pattern.compile("(978-3-)\\d{3}-\\d{5}-\\d");
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
