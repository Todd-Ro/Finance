package com.company;

public class NPV {



    double[][] cashFlows;
    double r;
    double[] PVTable;



    public void setCashFlows(double[][] cashFlows) {
        /*
        Enter pairs; First value is time and second is flow.
         */
        this.cashFlows = cashFlows;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double[] generatePVTable() {
        int len = cashFlows.length;
        if (len == 0) {
            return null;
        }
        else {
            double[] ret = new double[len];
            for (int i = 0; i < len; i++) {
                double value = cashFlows[i][1];
                ret[i] = value * Math.pow(1+r,-1*cashFlows[i][0]);
                //TODO: Fix so that this is discounted to current value
            }
            PVTable = ret;
            return ret;
        }
    };



    public double findNPV() {
        if (PVTable.length == 0) {
            generatePVTable();
        }
        return FinMathOps.sumArray(PVTable);
    }

}
