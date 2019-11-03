package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Binomial {

    public static boolean compEquals(double a, double b) {
        /*This method tests whether two numbers are essentially equal, to prevent inequalities caused by
        floating point errors.
         */

        double diff = a - b;
        if ( (diff * a * b <= 0.0000000000003) && (diff * a * b >= -0.0000000000003)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double calcProbUp(double u, double d, double r) {
        /* Returns the probability that an asset price will increase if markets are risk-neutral,
        the risk-free rate is r, and the asset's price is either multiplied by cUp if it increases
        or by cDown if it decreases.
        u should be greater than 1, while d should be between 0 and 1.
         */

        double profitUp = (u -1);
        /*profitUp is a positive number that represents the gain, as a multiple of the initial asset price,
        if the asset goes up in value.
         */

        double profitDown = (d -1);
        /*profitDown is a negative number that represents the "gain", as a multiple of the initial asset price, if the
        asset goes down in value.
        In other words, its absolute value represents the loss.
         */

        double p;
        // p is the probability that the asset price will increase; the variable we will return..

        /*Overall expected value of gain is p(u-1) + (1-p)(d-1). If asset markets are risk-neutral, this should equal
        r. So r = p(u-1) + (d-1) - p*(d-1)
        Rearranging this, (r - d + 1) = p (u  - d)
         */

        p = 0.5;

        try {
            p =  (r - d + 1) / (u -d);
            if (compEquals(r, (p*profitUp + (1-p)*profitDown))) {
                return round(p,15);
            }
            else {
                throw new Exception("A problem has occured in processing the calcProbUp inputs + \n"
                + "p was calculated to equal " + p + "\n"
                + "profitUp was calculated as " + profitUp +" and profitDown was calculated as " + profitDown);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        return p;
    }


}
