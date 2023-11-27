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

    /**
     * Returns the title based on the BMI
     * @param currentBMI
     * @return returns the title as a string
     */
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

    /**
     * Returns a description based off of the bmi.
     * @param currentBMI
     * @return a description for the BMI as a string
     */

    public static String getBMIDescription(double currentBMI) {
        String result = getBMITitle(currentBMI);

        if (result.equals(EXCEPT_UNDERWEIGHT)) {
            return Details.EXCEPT_UNDERWEIGHT;
        } else if (result.equals(UNDER_WEIGHT)) {
            return Details.UNDER_WEIGHT;
        } else if (result.equals(HEALTHY_WEIGHT)) {
            return Details.HEALTHY_WEIGHT;
        } else if (result.equals(OVER_WEIGHT)) {
            return Details.OVER_WEIGHT;
        } else if (result.equals(OBESITY_STAGE_1)) {
            return Details.OBESITY_STAGE_1;
        } else if (result.equals(OBESITY_STAGE_2)) {
            return Details.OBESITY_STAGE_2;
        }
        return Details.OBESITY_STAGE_3;
    }

    /**
     * Descriptions based off the BMI.
     */
    private static class Details
    {
        public static final String EXCEPT_UNDERWEIGHT = "Your weight is exceptionally low. Consider medical advice.";
        public static final String UNDER_WEIGHT = "You are considered underweight, consider increasing your calorie intake";
        public static final String HEALTHY_WEIGHT = "You are considered to have a healthy weight, consider keeping a balanced diet and stay active.";
        public static final String OVER_WEIGHT = "You are considered overweight. Consider lowering your calorie intake.";
        public static final String OBESITY_STAGE_1 = "You're weight is at an obesity of stage 1, consider a healthier lifestyle.";
        public static final String OBESITY_STAGE_2 = "You're weight is at an obesity of stage 2, consider medical advice.";
        public static final String OBESITY_STAGE_3 = "You're weight is at an obesity of stage 2, consider medical advice.";
    }
}
