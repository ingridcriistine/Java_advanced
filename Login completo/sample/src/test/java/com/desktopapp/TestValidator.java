package com.desktopapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestValidator {

    @Test
    void validateInvalidPasswords() {
        assertEquals(MyValidator.ValidatePassword(""), false); //senha vazia
        assertEquals(MyValidator.ValidatePassword("abcd"), false); //não possui 8 caracteres
        assertEquals(MyValidator.ValidatePassword("12345678"), false); //só possui números
        assertEquals(MyValidator.ValidatePassword(".,~´.;,.~´%%"), false); //só possui caracteres especiais
        assertEquals(MyValidator.ValidatePassword("minhasenha"), false); //só possui letras
        assertEquals(MyValidator.ValidatePassword("minhasenha..."), false); //não possui números
        assertEquals(MyValidator.ValidatePassword("minhasenha123"), false); //não possui caracteres especiais
        assertEquals(MyValidator.ValidatePassword("123456%%"), false); //não possui letras
    }

    @Test
    void validateValidPasswords() {
        assertEquals(MyValidator.ValidatePassword("minha.senha123"), true);
        assertEquals(MyValidator.ValidatePassword("senhaemoji.👻123"), true);
        assertEquals(MyValidator.ValidatePassword("oi2005.senha"), true);
        assertEquals(MyValidator.ValidatePassword(".oi.oi.oi123456"), true);
        
    }

    @Test
    void validateInvalidEmails() {
        assertEquals(MyValidator.ValidateEmail(""), false); //email vazio
        assertEquals(MyValidator.ValidateEmail("y.z"), false); // falta '@'
        assertEquals(MyValidator.ValidateEmail("@y.z"), false); // '@' no início
        assertEquals(MyValidator.ValidateEmail("x@.z"), false); // '.' logo após '@'
        assertEquals(MyValidator.ValidateEmail("x@y."), false); // '.' no final
        assertEquals(MyValidator.ValidateEmail("x@y"), false); // falta '.'
    }

    @Test
    void validateValidEmails() {
        assertEquals(MyValidator.ValidateEmail("x@y.z"), true);
    }

}