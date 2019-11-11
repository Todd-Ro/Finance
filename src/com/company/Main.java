package com.company;

public class Main {

    public static void main(String[] args) {
        double u = 1.25;
        double d = 0.7;
        double r = 0.03;
        double p = Binomial.calcProbUp(u, d, r);
        //p should equal 0.6
        System.out.println(p);
        double S = 34;
        double X = 30;
        double c = Binomial.currentValueTwoPeriodCall(u, d, r, S, X);
        // c should be approximately 7.85
        System.out.println(c);
    }
}
