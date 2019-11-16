package com.company;

public class Date {

    int year;
    int month;
    int day;
    boolean inLeapYear;

    public static int[] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};
    public static int[] daysSinceYearStartList = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
    public static int[] daysSinceLeapYearStartList = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366};

    int daysSinceYearStart;
    double time; // Represents years since calendar start; June 30 1977 would be about 1977.5.

    public void setInLeapYear(boolean inLeapYear) {
        this.inLeapYear = inLeapYear;
    }
    public boolean setInLeapYear() {
        boolean ret = isLeap(this.year);
        this.inLeapYear = ret;
        return ret;
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
        //Method assumes year between 0 and 99 is between 1970 (70) and 2069 (69)
        Date d = new Date();
        d.setMonth(input[0]);
        d.setDay(input[1]);
        int y;
        if ((input[2] >= 70) && (input[2] < 100)) {
            y = input[2] + 1900;
        }
        else if (input[2] < 70) {
            y = input[2] + 2000;
        }
        else {
            y = input[2]; // Assumes YYYY was used instead of YY if 100 or larger
        }
        d.setYear(y);
        d.setInLeapYear(isLeap(y));
        return d;
    }

    public static Date dateFromMMDDYYDoubles(double[] input) {
        int[] intDates = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            intDates[i] = (int) Math.round(input[i]);
        }
        return dateFromMMDDYY(intDates);
    }

    static int[] findDaysSinceYearStartList() {
        //Generates the same values that are in daysSinceYearStart
        int[] ret = new int[13];
        int j = 0;
        for (int i = 0; i<12; i++) {
            ret[i] = j;
            j += daysInMonths[i];
        }
        ret[12] = j;
        return ret;
    }

    static int[] findLeapDaysSinceYearStartList() {
        int[] ret = new int[13];
        ret[0] = daysSinceYearStartList[0];
        ret[1] = daysSinceYearStartList[1];
        for (int i=2; i<=12; i++) {
            ret[i] = (daysSinceYearStartList[i] + 1);
        }
        return ret;
    }

    int findDaysSinceYearStart() {
        //Will return 0 for January 1st
        int m = month - 1;
        int ret;
        boolean b = setInLeapYear();
        double t;
        if (b) {
            ret = daysSinceLeapYearStartList[m] + this.day - 1;
            t = ret / 366.0;
        }
        else {
            ret = daysSinceYearStartList[m] + this.day - 1;
            t = ret / 365.0;
        }
        this.daysSinceYearStart = ret;
        this.time = this.year + t;
        return ret;
    }

    double findTime() {
        findDaysSinceYearStart();
        return this.time;
    }

    /*int DaysSince(Date dPast) {
        if (dPast.getYear() == year) {

        }
    }*/

    public static int findDayOfYearFromMonthAndDay(int[] monthDayYear) {
        //Returns day of year. input month, then day, then year. For month 2, day 1, returns 32
       int year = monthDayYear[2];
       int month = monthDayYear[0];
       int day = monthDayYear[1];
       if (isLeap(year) == false) {
           return (daysSinceYearStartList[month-1] + day);
       }
       else {
           return (daysSinceLeapYearStartList[month-1] + day);
       }
    }

    public static int[] findMonthAndDayFromDayOfYear(int day, int year) {
        // Returns month, then day. For February 1, input 32 then the year
        if (isLeap(year) == false) {
            int[] monthInfo =
                    FinMathOps.findLargestValueBelowTargetInAscendingArray(daysSinceYearStartList, day-1);
            int monthIndex = monthInfo[1];
            return new int[] {monthIndex+1, day - monthInfo[0]};
        }
        else {
            int[] monthInfo =
                    FinMathOps.findLargestValueBelowTargetInAscendingArray(daysSinceLeapYearStartList, day-1);
            int monthIndex = monthInfo[1];
            return new int[] {monthIndex-1, day - monthInfo[0]};
        }
    }

    public static int findDayNumberFromTime(double time) {
        int year = (int) time;
        double dayPortion = time % 1;
        if (isLeap(year) == false) {
            return (int) (Math.round(dayPortion*365) + 1);
            // 1 is added because time is indexed from January first being year + 0.0
        }
        else {
            return (int) (Math.round(dayPortion*366) + 1);
        }
    }

}
