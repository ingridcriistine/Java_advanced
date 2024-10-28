package com.desktopapp;

public class MyValidator {
    
    public static boolean ValidatePassword(String password) {

        if (password == null || password.isEmpty()) {
            return false;
        }

        if (password.length() < 8)
            return false;
        
        if (password.length() > 7)
            return true;
        
        return password.chars()
            .anyMatch(c -> c >= '0' && c <= '9');
    }

    public static boolean ValidateEmail (String email) {

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
