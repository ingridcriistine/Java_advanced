package com.desktopapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class EmailValidatorTests {

    @Test
    void validateInvalidEmails() {
        assertEquals(MyEmailValidator.Validate(""), false); // vazio
        assertEquals(MyEmailValidator.Validate("email.com"), false); // falta '@'
        assertEquals(MyEmailValidator.Validate("@example.com"), false); // '@' no início
        assertEquals(MyEmailValidator.Validate("example@.com"), false); // '.' logo após '@'
        assertEquals(MyEmailValidator.Validate("example@com."), false); // '.' no final
        assertEquals(MyEmailValidator.Validate("example@com"), false); // falta '.'
    }

    @Test
    void validateValidEmails() {
        assertEquals(MyEmailValidator.Validate("user@example.com"), true);
        assertEquals(MyEmailValidator.Validate("my.email@domain.org"), true);
        assertEquals(MyEmailValidator.Validate("name.surname@company.co"), true);
        assertEquals(MyEmailValidator.Validate("test.email+filter@domain.com"), true);
    }
}