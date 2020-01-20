package com.company.BlackScholes;

public class CallValuation {

    public static double cumulStandardNormDist(double x) {
        //Piecewise approximation for the cumulative standard normal distribution
        if (x > 0) {
            return 1-cumulStandardNormDist(-x);
        }
        else {
            if (x > -0.42594) {
                return 0.0365 * x * x + 0.4035 * x + 0.5;
            }
            if (x > -0.7151) {
                return 0.0962 * x * x + 0.4483 * x + 0.5086;
            }
            if (x > -1.01162) {
                return 0.1182 * x * x + 0.4787 * x + 0.5192;
            }
            if (x > -1.3452) {
                return 0.1171 * x * x + 0.4755 * x + 0.517;
            }
            if (x > 1.7226) {
                return 0.0946 * x * x + 0.4138 * x + 0.4747;
            }
            if (x > -2.1515) {
                return 0.06 * x * x + 0.2942 * x + 0.3712;
            }
            if (x > -2.641) {
                if (x > -2.3291) {
                    return 0.0365 * x * x + 0.196 * x + 0.2685;
                } else {
                    return 0.023 * x * x + 0.1326 * x + 0.1941;
                }
            }
            if (x > -3.2) {
                if (x > -2.7205) {
                    return 11.045 * Math.exp(2.9876 * x);
                }
                if (x > -2.823) {
                    return 13.861 * Math.exp(3.071 * x);
                }
                if (x > -3.009) {
                    return 20.096 * Math.exp(3.202 * x);
                } else {
                    return 34.034 * Math.exp(3.3773 * x);
                }
            }
            if (x > -3.85) {
                if (x > -3.293) {
                    return 52.36 * Math.exp(3.5127 * x);
                }
                if (x > -3.455) {
                    return 77.454 * Math.exp(3.6312 * x);
                }
                if (x > -3.67) {
                    return 139.24 * Math.exp(3.8008 * x);
                } else {
                    return 282.64 * Math.exp(3.9945 * x);
                }
            } else {
                if (x > -3.95) {
                    return 483.29 * Math.exp(4.1344 * x);
                }
                if (x > -4.1) {
                    return 768.41 * Math.exp(4.2516 * x);
                }
                if (x > -4.2) {
                    return 1193.9 * Math.exp(4.3597 * x);
                } else {
                    return 2846.4 * Math.exp(4.5651 * x);
                }
            }
        }
    }

}
