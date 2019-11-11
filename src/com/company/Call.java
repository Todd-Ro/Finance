package com.company;

public class Call {

    double callCost;
    double exercisePrice;

    double exerciseReturn(double assetPriceAtExercise) {
        if (assetPriceAtExercise < exercisePrice) {
            return 0;
        }
        else {
            return (assetPriceAtExercise - exercisePrice);
        }
    }

    double totalReturn(double assetPriceAtExercise) {
        return (exerciseReturn(assetPriceAtExercise) - callCost);
    }
}
