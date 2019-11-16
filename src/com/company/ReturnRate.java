package com.company;

import static com.company.FinMathOps.sumArray;

public class ReturnRate extends NPV {

    // It is intended that dates be entered in chronological order starting with the earliest
    double[][] datePriceDiv; //Input date in mm-dd-yy, then price, then dividend
    double[][] timePriceDiv;
    //double r;
    //double[] PVTable;


    public void setDatePriceDiv(double[][] datePriceDiv) {
        this.datePriceDiv = datePriceDiv;
    }

    public void setTimePriceDiv(double[][] timePriceDiv) {
        this.timePriceDiv = timePriceDiv;
    }



    public double[][] setTimePriceDivByTransfer() {
        int len = datePriceDiv.length;
        timePriceDiv = new double[len][3];
        for (int i = 0; i < len; i++) {
            double[] dateNums = FinMathOps.getFirstThree(datePriceDiv[i]);
            Date d = Date.dateFromMMDDYYDoubles(dateNums);
            double t = d.findTime();
            timePriceDiv[i][0] = t;
            timePriceDiv[i][1] = datePriceDiv[i][3];
            timePriceDiv[i][2] = datePriceDiv[i][4];
        }
        return timePriceDiv;
    }

    double[][] periodReturns;
    /*  Represents the return from one entered date to the next, divided into capital gain component first
        and then dividend gain component. For example, if the asset rose in value 15% from one entered date
        to the next, the capital gain would be .15, regardless of how far apart in time the two dates were.
    */

    double[][] findPeriodReturns() {
        // Assumes datePriceDiv has been initialized properly if timePriceDiv has not
        // Assumes price info has been entered for at least two dates
        /* Each entry is a set of doubles with time first, then capital gain, then dividend return,
            then number of shares immediately prior to reinvestment if reinvested without transaction costs*/
        /* Output has one fewer entry than the input data, since it is the return over each interval of two consecutive
            points in time*/
        // Ignores any dividend at the initial time
        double[][] info;
        if (timePriceDiv.length == 0) {
            info = setTimePriceDivByTransfer();
        }
        else {
            info = timePriceDiv;
        }
        int timePeriods = info.length;
        double[][] ret = new double[timePeriods-1][4];
        for (int i=1; i<timePeriods; i++) {
            ret[i-1][0] = info[i][0];
            ret[i-1][1] = ((info[i][1] / info[i-1][1]) - 1);
            ret[i-1][2] = ((info[i][2] / info[i-1][1]));
        }
        ret[0][3] = (100.0);
        if (timePeriods > 2) {
            for (int i = 2; i<timePeriods; i++) {
                ret[i-1][3] = (ret[i-2][3] * (1 + (info[i-1][2] / info[i-1][1])));
            }
        }
        this.periodReturns = ret;
        return ret;
    }

    double[][] overallReturnsSeries;

    double[][] findOverallReturns() {
        //Time, then overall return rate
        if (periodReturns.length == 0) {
            findPeriodReturns();
        }
        double[][] ret = new double[periodReturns.length][2];
        for (int i = 0; i < periodReturns.length; i++) {
            ret[i][0] = periodReturns[i][0];
            ret[i][1] = periodReturns[i][1] + periodReturns[i][2];
        }
        overallReturnsSeries = ret;
        return ret;
    }

    double findTotalReturnMult() {
        double[][] overallReturns = findOverallReturns();
        double value = 1;
        for (int i = 0; i < overallReturns.length; i++) {
            value += (value * overallReturns[i][1]);
        }
        return FinMathOps.round(value,9);
    }

    double[] findFinalMarketCap() {
        /* Generates final share count, final market capitalization, final dividend amount, and overall return if
            returns were reinvested without transaction costs*/
        if (periodReturns.length == 0) {
            findPeriodReturns();
        }
        int lenPeriods = periodReturns.length;
        int lenTPD = timePriceDiv.length;
        double[] retu = new double[4];
        double[][] info = timePriceDiv;
        retu[0] = FinMathOps.round(periodReturns[lenPeriods-1][3],10);
        retu[1] = FinMathOps.round((retu[0] * info[lenTPD-1][1]),10);
        retu[2] = (retu[0] * info[lenTPD-1][2]);
        retu[3] = FinMathOps.round(((retu[1] + retu[2]) / (periodReturns[0][3] * timePriceDiv[0][1]) - 1),9);
        return retu;
    }











}
