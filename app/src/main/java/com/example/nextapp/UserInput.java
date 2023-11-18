package com.example.nextapp;

public class UserInput {
    private double userHeight;
    private double userWeight;
    private double result;

    public UserInput() {

    }

    public UserInput(double userHeight, double userWeight) {
        this.userHeight = userHeight;
        this.userWeight = userWeight;
    }

    public double getUserHeight() {
        return userHeight;
    }

    public double getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(double userWeight) {
        this.userWeight = userWeight;
    }

    public void setUserHeight(double userHeight) {
        this.userHeight = userHeight;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
