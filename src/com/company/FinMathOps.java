package com.company;

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
}
