package com.example.bibliophilia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class dvIsbnNumberTest {

    @Test
    public void twoIsbnEqual() {
        assertEquals(dvIsbnNumber.valueOf("978-3-123-12345-0"), dvIsbnNumber.valueOf("978-3-123-12345-0"));
    }

    @Test
    public void isValid() {
        assertTrue(dvIsbnNumber.isValid("978-3-123-12345-0"));
        assertTrue(dvIsbnNumber.isValid("978-1-567-12345-9"));
    }

    @Test
    public void isNotValid() {
        assertFalse(dvIsbnNumber.isValid("123-3-123-12345-0"));
        assertFalse(dvIsbnNumber.isValid("9783-123-12345-0"));
        assertFalse(dvIsbnNumber.isValid("978-3123-12345-0"));
        assertFalse(dvIsbnNumber.isValid("978-3-12312345-0"));
        assertFalse(dvIsbnNumber.isValid("978-3-123-123450"));
        assertFalse(dvIsbnNumber.isValid("978"));
        assertFalse(dvIsbnNumber.isValid("abc"));
    }

}
