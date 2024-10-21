package com.desktopapp;

public class DateValidator {
    public Boolean validate(int day, int month, int year) {
        if (day < 1 || day > 31)
            return false;
        if (month < 1 || month > 12)
            return false;
        if (day < 29)
            return true;
        // for days 31, 30 or 29
        if (day == 31)
            return month == 1 ||
                    month == 3 || month == 5 ||
                    month == 7 || month == 8 ||
                    month == 10 || month == 12;
        if (day == 30)
            return month != 2;
        // for day 29
        if (month != 2)
            return true;
        return year % 4 == 0 && (year % 100 != 0 ||
                year % 400 == 0);
    }
}