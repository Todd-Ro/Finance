package com.company;

public class Date {

    int year;
    int month;
    int day;
    boolean inLeapYear;
    public static int[] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};

    public void setInLeapYear(boolean inLeapYear) {
        this.inLeapYear = inLeapYear;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public static boolean isLeap(int year) {
        if (year % 400 == 0) {
            return true;
        }
        else if (year % 100 == 0) {
            return false;
        }
        else if (year % 4 == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Date dateFromMMDDYY(int[] input) {
        Date d = new Date();
        d.setMonth(input[0]);
        d.setDay(input[1]);
        int y;
        if (input[2] >= 70) {
            y = input[2] + 1900;
        }
        else {
            y = input[2] + 2000;
        }
        d.setYear(y);
        d.setInLeapYear(isLeap(y));
        return d;
    }

    /*int DaysSince(Date dPast) {
        if (dPast.getYear() == year) {

        }
    }*/

}
