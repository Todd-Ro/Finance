package com.company;

public class Put {

    double putCost;
    double exercisePrice;

    double exerciseReturn(double assetPriceAtExercise) {
        if (assetPriceAtExercise > exercisePrice) {
            return 0;
        }
        else {
            return (exercisePrice - assetPriceAtExercise);
        }
    }

    double totalReturn(double assetPriceAtExercise) {
        return (exerciseReturn(assetPriceAtExercise) - putCost);
    }

}


