package com.company.BlackScholes;

public class CallValuation {

    public static double cumulStandardNormDist(double x) {
        if (x <= 0) {
            if (x > -0.42594) {
                return 0.3919*x + 0.5;
            }
            if (x > -0.7151) {
                return 0.3389*x + 0.4782;
            }
            if (x > -1.01162) {
                return 0.6563*Math.exp(1.4151*x);
            }
            if (x > -1.3452) {
                return 0.8471*Math.exp(1.6665*x);
            }
            if (x > 1.7226) {
                return 1.2609*Math.exp(1.9616*x);
            }
            if (x > -2.1515) {
                return 0.0946*x*x + 0.4138*x + 0.4747;
            }
            if (x > -2.641) {
                return 5.4446*Math.exp(2.7113*x);
            }
            if (x > -3.2) {
                return 18.726*Math.exp(3.1811*x);
            }
            if (x > -3.82) {
                return 105.44*Math.exp(3.7252*x);
            }
            else {
                return 1268.1*Math.exp(4.3788*x);
            }
        }
        else {
            return 1-cumulStandardNormDist(-x);
        }
    }

}
