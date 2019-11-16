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
        System.out.println("");


        double[][] flows = {{0, -16500}, {1, 5100}, {2, 7200}, {3, 7200}, {4, 7200}, {5, 2700}};
        double rate = .241;
        NPV projectCheck = new NPV();
        projectCheck.setCashFlows(flows);
        projectCheck.setR(rate);
        double[] check = projectCheck.generatePVTable();
        System.out.println(Arrays.toString(check));
        double sum = projectCheck.findNPV();
        System.out.println(sum); // Should have value close to breakeven
        System.out.println("");

        System.out.println(FinMathOps.sumArray(Date.daysInMonths)); // Should be 365
        int[] monthInts = Date.findDaysSinceYearStartList();
        System.out.println(Arrays.toString(monthInts));
        System.out.println(Arrays.toString(Date.findLeapDaysSinceYearStartList()));
        int[] midYear = {7,3,77};
        Date dat = Date.dateFromMMDDYY(midYear);
        System.out.println(dat.findTime());
        double[] midYearDub = {7.0, 3.0, 77.0};
        Date dat2 = Date.dateFromMMDDYYDoubles(midYearDub);
        System.out.println(dat2.findTime());
        System.out.println("80 days into year " + Arrays.toString(
                FinMathOps.findLargestValueBelowTargetInAscendingArray(monthInts, 80)));
        System.out.println("1 day into year " + Arrays.toString(
                FinMathOps.findLargestValueBelowTargetInAscendingArray(monthInts, 1)));
        System.out.println("350 days into year " + Arrays.toString(
                FinMathOps.findLargestValueBelowTargetInAscendingArray(monthInts, 350)));
        System.out.println("0 days into year " + Arrays.toString(
                FinMathOps.findLargestValueBelowTargetInAscendingArray(monthInts, 0)));
        System.out.println("31 days have ended " + Arrays.toString(
                FinMathOps.findLargestValueBelowTargetInAscendingArray(monthInts, 31)));
        System.out.println(Date.findDayOfYearFromMonthAndDay(new int[]{2,1,2019}));
        System.out.println(Arrays.toString(Date.findMonthAndDayFromDayOfYear(32, 2019)));
        System.out.println(Arrays.toString(Date.findMonthAndDayFromDayOfYear(365, 2019)));
        int[] startFeb = {2,1,19};
        Date dat3 = Date.dateFromMMDDYY(startFeb);
        double febTime = dat3.findTime();
        System.out.println(Date.findDayNumberFromTime(febTime));
        System.out.println("");

        double[][] pricesTable = {{12,31,3, 27.37, 0}, {8,23,4, 27.24, .08}, {11,15,4, 27.39, 3.08},
                {12,31,4, 26.72, 0}};
        System.out.println(Arrays.toString(FinMathOps.getFirstThree(pricesTable[0])));
        ReturnRate returnRateAsis = new ReturnRate();
        returnRateAsis.setDatePriceDiv(pricesTable);
        double[][] pricesTableTimes = returnRateAsis.setTimePriceDivByTransfer();
        System.out.println(Arrays.toString(pricesTableTimes[0]) + Arrays.toString(pricesTableTimes[1]) +
                Arrays.toString(pricesTableTimes[2]) + Arrays.toString(pricesTableTimes[3]));
        double[][] returnsOverTime = returnRateAsis.findPeriodReturns();
        System.out.println(Arrays.toString(returnsOverTime[0]) + Arrays.toString(returnsOverTime[1]) +
                Arrays.toString(returnsOverTime[2]));
        double[][] totalReturns = returnRateAsis.findOverallReturns();
        System.out.println(Arrays.toString(totalReturns[0]) + Arrays.toString(totalReturns[1]) +
                Arrays.toString(totalReturns[2]));
        System.out.println(returnRateAsis.findTotalReturnMult());
        System.out.println(Arrays.toString(returnRateAsis.findFinalMarketCap()));
    }
}
