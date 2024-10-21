package com.desktopapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.desktopapp.DateValidator;

// A classe deve terminar com 'Test' no nome.
class MyTest {
    private final DateValidator validator = new DateValidator();

    @Test
    void validate() {
        assertEquals(
                validator.validate(29, 2, 2024),
                true);
        assertEquals(
                validator.validate(29, 2, 2025),
                false);
        assertEquals(
                validator.validate(30, 2, 2024),
                false);
        assertEquals(
                validator.validate(30, 12, 1800),
                true);
        assertEquals(
                validator.validate(29, 2, 1800),
                false);
        assertEquals(
                validator.validate(29, 2, 1600),
                true);
        assertEquals(
                validator.validate(4, 1, 2023),
                true);
        assertEquals(
                validator.validate(31, 4, 2000),
                false);
        assertEquals(
                validator.validate(29, 4, 2001),
                true);
    }
}