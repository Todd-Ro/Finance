package com.company;

import java.util.Arrays;

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


        double[][] flows = {{0, -16500}, {1, 5100}, {2, 7200}, {3, 7200}, {4, 7200}, {5, 2700}};
        double rate = .241;
        NPV projectCheck = new NPV();
        projectCheck.setCashFlows(flows);
        projectCheck.setR(rate);
        double[] check = projectCheck.generatePVTable();
        System.out.println(Arrays.toString(check));
        double sum = projectCheck.findNPV();
        System.out.println(sum); // Should have value close to breakeven

        System.out.println(FinMathOps.sumArray(Date.daysInMonths)); // Should be 365
    }
}
