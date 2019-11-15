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
}
