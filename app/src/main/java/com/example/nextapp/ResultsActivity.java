package com.example.nextapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nextapp.descriptions.Description;
import com.google.android.material.slider.Slider;

public class ResultsActivity extends AppCompatActivity {

    private Double bmiValue;
    private TextView bmiResult;
    private Slider bmiSlider;
    private ImageButton backButton;
    private TextView bmiResultTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        Intent intent = getIntent();
        bmiValue = intent.getDoubleExtra("bmi_result", 0.0);


        initializeButtons();
        setBMIMessage();
        initializeBMISlider();
        setBMIResultTitle();
    }

    /**
     * Sets the buttons with their
     * respective IDs.
     */
    private void initializeButtons() {
        bmiResult = findViewById(R.id.userBMI);
        bmiSlider = findViewById(R.id.userBMISlider);
        backButton = findViewById(R.id.bmiBackButton);
        bmiResultTitle = findViewById(R.id.bmiResultTitle);
        initializeBackButton();
    }

    /**
     * Sets the title for the BMI Result
     */
    private void setBMIResultTitle() {
        bmiResultTitle.setText(Description.getBMITitle(bmiValue.doubleValue()));
        setTextViewColor(bmiResultTitle);
    }


    /**
     * Changes the textview color based on the bmi value.
     * @param textView
     */
    private void setTextViewColor(TextView textView) {
        ColorStateList color = ColorStateList.valueOf(colorBasedOnBMI(bmiValue));
        textView.setTextColor(color);
    }

    /**
     * Ensures when the back button is clicked
     * the user is able to go back to the main
     * screen.
     */
    private void initializeBackButton() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * If the result seems invalid
     * return a default error message
     * @return String
     */
    private String invalidResult() {
        String bmiOfZero = "Possible Invalid Result";
        return bmiOfZero;
    }

    /**
     * Sets the value and color of the slider.
     */
    private void initializeBMISlider() {
        bmiSlider.setValue((float) bmiValue.doubleValue());
        // Disable changing of the bmi value
        bmiSlider.setEnabled(false);

        int bmiColor = colorBasedOnBMI(bmiValue);
        bmiSlider.setThumbTintList(ColorStateList.valueOf(bmiColor));
    }


    private boolean isHealthyWeight(double currentBMI) {
        return (currentBMI > 18.5) && (currentBMI < 25.0);
    }

    /**
     * Evaluates the color based on the BMI.
     * @param currentBMI
     * @return Color object based on BMI
     */
    private int colorBasedOnBMI(double currentBMI) {
        double bmiMassWeightDeficient = 16.0;
        double bmiWeightDeficient = 18.5;
        double bmiOverWeight = 25.0;
        double bmiObeseFirstDegree = 30.0;
        double bmiObeseSecondDegree = 35.0;
        double bmiObeseThirdDegree = 40.0;

        if (currentBMI < bmiMassWeightDeficient) {
            return Color.rgb(199, 0, 0);
        } else if (currentBMI < bmiWeightDeficient) {
            return Color.rgb(249, 191, 0);
        } else if (isHealthyWeight(currentBMI)) {
            return Color.GREEN;
        } else if (currentBMI < bmiObeseFirstDegree) {
            return Color.rgb(249, 191, 0);
        } else if (currentBMI < bmiObeseSecondDegree) {
            return Color.rgb(199, 0, 0);
        } else if (currentBMI < bmiObeseThirdDegree) {
            return Color.rgb(102, 0, 0);
        }
        return Color.rgb(102, 0, 0);
    }

    /*
    Displays the BMI on the screen
     */
    private void setBMIMessage() {
        // Round to two decimal places for formatting.
        String twoDecimalBMI = String.format("%.2f", bmiValue);


        if ((twoDecimalBMI == "0.00"))
        {
            Toast.makeText(this, invalidResult(), Toast.LENGTH_LONG);
        }

        bmiResult.setText(twoDecimalBMI);
    }
}