package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Binomial {

    public static boolean compEquals(double a, double b) {
        /*This method tests whether two numbers are essentially equal, to prevent inequalities caused by
        floating point errors.
         */

        double diff = a - b;
        if ( Math.abs(diff)  <= ( ((a + b)/2) * 0.0000000000003) ) {
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
                return round(p,13);
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

    public static double afterOnePeriodCallValuation(double u, double d, double r, double S, double X) {
        /*
        The extra values here that are not found in calcProbUp are S, the current asset price, and c, the call value
        of an option.
        The goal of this function is to find the expected value, and therefore the risk-neutral valuation, of an option
        with exercise price X on an asset with current value S after one period in which the asset either appreciates
        to a value of uS or depreciates to a value of dS.
         */

        double p = calcProbUp(u, d, r);
        double cPlus = Math.max(u*S - X, 0); // cPlus is the exercise value if the asset increases in value.
        double cMinus = Math.max(d*S - X, 0); // cMinus is the exercise value of the asset decreases in value, often 0.
        return round(p*cPlus + (1-p) * cMinus, 13);
    }

    public static double currentValueOnePeriodCall(double u, double d, double r, double S, double X) {
        /*
        Returns the current value of a one-period European call option, as opposed to the expected value of its value
        at the exercise date (which is what afterOnePeriodCallValuation returns).
         */
        double valueTOne = afterOnePeriodCallValuation(u, d, r, S, X);
        return round((valueTOne / (1 + r)), 13);
    }

    public static double afterTwoPeriodCallValuation (double u, double d, double r, double S, double X) {
        /*
        The goal of this function is to find the expected value, and therefore the risk-neutral valuation, of an option
        with exercise price X on an asset with current value S after TWO periods.
         */

        double p = calcProbUp(u, d, r);
        double cPlusPlus = Math.max(u*u*S - X, 0);
        // cPlusPlus is the exercise value if the asset increases in value twice.
        double cPlusMinus = Math.max(u*d*S - X, 0);
        // cPlusMinus is the exercise value of the asset increases in value once and decreases in value once.
        double cMinusMinus = Math.max(d*d*S - X, 0);
        // cMinusMinus is the exercise value if the asset decreases in value twice, often zero.
        return round(p*p*cPlusPlus + p*(1-p)*2*(cPlusMinus) + (1-p)*(1-p)*cMinusMinus,13);
    }

    public static double currentValueTwoPeriodCall (double u, double d, double r, double S, double X) {
        /*
        Returns the current value of a European call option with an exercise date after two periods,
        where each period the asset either increases in value to a multiple of u or decreases in value to a multiple
        of d.
         */
        double valueTTwo = afterTwoPeriodCallValuation(u, d, r, S, X);
        return round((valueTTwo / ((1+r)*(1+r)) ), 10);
    }

}
