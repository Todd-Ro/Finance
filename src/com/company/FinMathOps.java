package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FinMathOps {

    public static double sumArray(double[] thisArray) {
        int len = thisArray.length;
        if (len == 0) {
            return 0;
        }
        else {
            double sum = 0;
            for (int j = 0; j < len; j++) {
                sum += thisArray[j];
            }
            return sum;
        }
    }

    public static int sumArray(int[] thisArray) {
        int len = thisArray.length;
        if (len == 0) {
            return 0;
        }
        else {
            int sum = 0;
            for (int j = 0; j < len; j++) {
                sum += thisArray[j];
            }
            return sum;
        }
    }


    public static double[] getFirstThree(double[] entry) {
        // Gets the first three entries from an array with at least three entries.
        if (entry.length < 3) {
            return null;
        }
        double[] ret = new double[3];
        ret[0] = entry[0];
        ret[1] = entry[1];
        ret[2] = entry[2];
        return ret;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static boolean isArrayInAscendingOrder(int[] array) {
        if (array.length == 0) {
            return false;
        }
        if (array.length == 1) {
            return true;
        }
        int prev = array[0];
        for (int i=1; i<array.length; i++) {
            if (array[i] < prev) {
                return false;
            }
            prev = array[i];
        }
        return true;
    }

    public static int[] findLargestValueBelowTargetInAscendingArray(int[] sorted, int target) {
        /* Returns largest value below or equal to target, array position,
                    and the next value that is greater if present.
                    If tied values are equal to the target, the _____ one will be used.
                    Assumes smallest value in array is less than or equal to target*/
        if (isArrayInAscendingOrder(sorted) == false) {
            return null;
        }
        if (sorted[0] > target) {
            return null;
        }
        int latest;
        int i = 0;
        while (i < sorted.length) {
            latest = sorted[i];
            if(latest > target) {
                int[] ret = {sorted[i-1], i-1, latest};
                return ret;
            }
            i++;
        }
        int[] ret = {sorted[sorted.length-1],sorted.length-1};
        return ret;
    }
}
