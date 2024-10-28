package com.desktopapp;

public class MyEmailValidator {

    public static boolean Validate(String email) {

        // Regras simples para um e-mail válido:
        // - Deve conter um '@'
        // - Deve conter um '.'
        // - '@' não pode estar no início ou no final
        // - '.' não pode estar no início ou no final
        // - '@' deve aparecer antes de '.'

        if (email == null || email.isEmpty()) {
            return false;
        }
        
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');
        
        if (atIndex < 1 || dotIndex < atIndex + 2 || dotIndex >= email.length() - 1) {
            return false;
        }

        return true;
    }
}