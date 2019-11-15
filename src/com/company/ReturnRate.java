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

    /*double[][] findPeriodReturns() {
        // Assumes datePriceDiv has been initialized properly

    }*/







   

}
