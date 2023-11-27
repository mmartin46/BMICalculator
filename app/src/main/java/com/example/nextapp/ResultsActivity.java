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

import java.util.HashMap;

enum Category {
    BACK_BUTTON,
    SHARE_BUTTON,
    TITLE,
    DESCRIPTION,
    RESULT
}

public class ResultsActivity extends AppCompatActivity {

    private Double bmiValue;
    private Slider bmiSlider;

    private HashMap<Category, ImageButton> buttons;
    private HashMap<Category, TextView> textViews;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        Intent intent = getIntent();
        bmiValue = intent.getDoubleExtra("bmi_result", 0.0);

        initializeMembers();
    }

    /**
     * Sets the members with their
     * respective IDs.
     */
    private void initializeMembers() {
        bmiSlider = findViewById(R.id.userBMISlider);

        initializeButtons();
        initializeBMISlider();
        initializeTextViews();
    }

    private void initializeButtons() {
        buttons = new HashMap<>();
        buttons.put(Category.BACK_BUTTON, findViewById(R.id.bmiBackButton));
        buttons.put(Category.SHARE_BUTTON, findViewById(R.id.bmiShareButton));
        initializeBackButton();
    }

    /**
     * Initializes the needed text views to enable editing.
     */
    private void initializeTextViews() {
        textViews = new HashMap<>();
        textViews.put(Category.TITLE, findViewById(R.id.bmiResultTitle));
        textViews.put(Category.DESCRIPTION, findViewById(R.id.bmiResultDescription));
        textViews.put(Category.RESULT, findViewById(R.id.userBMI));
        setBMIResultTitle();
        setBMIDescription();
        setBMIMessage();
    }

    private void setBMIDescription() {
        textViews.get(Category.DESCRIPTION).setText(Description.getBMIDescription(bmiValue));
    }

    /**
     * Sets the title for the BMI Result
     */
    private void setBMIResultTitle() {
        textViews.get(Category.TITLE).setText(Description.getBMITitle(bmiValue));
        setTextViewColor(textViews.get(Category.TITLE));
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
        buttons.get(Category.BACK_BUTTON).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Ensures when the share button is clicked
     * the user is able to send their information.
     */
    private void initializeShareButton() {
        buttons.get(Category.SHARE_BUTTON).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FIXME: Share results via message, email, etc.
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
            Toast.makeText(this, invalidResult(), Toast.LENGTH_LONG).show();
        }

        // Sets the result.
        textViews.get(Category.RESULT).setText(twoDecimalBMI);
    }
}