package com.example.nextapp.descriptions;

import android.graphics.Color;



public class Description {

    public static final String EXCEPT_UNDERWEIGHT = "Exceptionally Underweight";
    public static final String UNDER_WEIGHT = "Underweight";
    public static final String HEALTHY_WEIGHT = "Healthy Weight";
    public static final String OVER_WEIGHT = "Overweight";
    public static final String OBESITY_STAGE_1 = "Obesity Stage 1";
    public static final String OBESITY_STAGE_2 = "Obesity Stage 2";
    public static final String OBESITY_STAGE_3 = "Obesity Stage 3";


    private static boolean isHealthyWeight(double currentBMI) {
        return (currentBMI > 18.5) && (currentBMI < 25.0);
    }

    public static String getBMITitle(double currentBMI) {
        double bmiMassWeightDeficient = 16.0;
        double bmiWeightDeficient = 18.5;
        double bmiOverWeight = 25.0;
        double bmiObeseFirstDegree = 30.0;
        double bmiObeseSecondDegree = 35.0;
        double bmiObeseThirdDegree = 40.0;



        if (currentBMI < bmiMassWeightDeficient) {
            return EXCEPT_UNDERWEIGHT;
        } else if (currentBMI < bmiWeightDeficient) {
            return UNDER_WEIGHT;
        } else if (isHealthyWeight(currentBMI)) {
            return HEALTHY_WEIGHT;
        } else if (currentBMI < bmiObeseFirstDegree) {
            return OVER_WEIGHT;
        } else if (currentBMI < bmiObeseSecondDegree) {
            return OBESITY_STAGE_1;
        } else if (currentBMI < bmiObeseThirdDegree) {
            return OBESITY_STAGE_2;
        }
        return OBESITY_STAGE_3;
    }

    public static String getBMIDescription(double currentBMI) {
        String result = getBMITitle(currentBMI);

        if (result.equals("Exceptionally Underweight")) {

        }
        // FIXME: NOT FINISHED
        return "";
    }
}
