package com.example.nextapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

enum ErrorType {
    INVALID_HEIGHT,
    INVALID_WEIGHT,
    VALID_STATUS
}

public class MainActivity extends AppCompatActivity {

    final double POUND_TO_KILO = 2.2046;
    final double INT_TO_CENT = 2.54;
    final int TWO_DIGIT_BMI_CONVERSION = 10000;

    private UserInput userInput;
    private WhichButton heightType;
    private WhichButton weightType;
    private LinearLayout topBar;

    private ConfigButtons configButtons;
    private AppCompatButton submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Intializing objects
        userInput = new UserInput();
        configButtons = new ConfigButtons();
        submitButton = findViewById(R.id.resultsButton);

        // Giving a default height and weight type
        // if not changed.
        heightType = WhichButton.INCHES;
        weightType = WhichButton.POUNDS;

        // Initializing the toggle buttons.
        initializeToggleButtons();
        submitButton();
    }

    /**
     * Initializes the buttons with the views of the layout.
     */
    private void initializeToggleButtons() {
        ToggleButton poundButton = findViewById(R.id.poundsButton);
        ToggleButton kilogramsButton = findViewById(R.id.kilogramsButton);
        ToggleButton inchesButton = findViewById(R.id.inchesButton);
        ToggleButton centimetersButton = findViewById(R.id.centimetersButton);

        configButtons.addButton(poundButton);
        configButtons.addButton(kilogramsButton);
        configButtons.addButton(inchesButton);
        configButtons.addButton(centimetersButton);
        setupHeightButtons();
        setupWeightButtons();
    }

    /**
     * Checks the type of the measurement the user
     * wants to use for height.
     */
    private void setupHeightButtons() {
        ConfigButtons.buttons.get(WhichButton.INCHES.ordinal()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConfigButtons.buttons.get(WhichButton.CENTIMETERS.ordinal()).isChecked() == true) {
                    heightType = WhichButton.INCHES;
                    ConfigButtons.buttons.get(WhichButton.CENTIMETERS.ordinal()).setChecked(false);
                    ConfigButtons.buttons.get(WhichButton.INCHES.ordinal()).setChecked(true);
                } else {
                    heightType = WhichButton.CENTIMETERS;
                    ConfigButtons.buttons.get(WhichButton.CENTIMETERS.ordinal()).setChecked(true);
                    ConfigButtons.buttons.get(WhichButton.INCHES.ordinal()).setChecked(false);
                }
            }
        });

        ConfigButtons.buttons.get(WhichButton.CENTIMETERS.ordinal()).setOnClickListener(v -> {
            if (ConfigButtons.buttons.get(WhichButton.CENTIMETERS.ordinal()).isChecked() == true) {
                heightType = WhichButton.CENTIMETERS;
                ConfigButtons.buttons.get(WhichButton.INCHES.ordinal()).setChecked(false);
                ConfigButtons.buttons.get(WhichButton.CENTIMETERS.ordinal()).setChecked(true);
            } else {
                heightType = WhichButton.INCHES;
                ConfigButtons.buttons.get(WhichButton.INCHES.ordinal()).setChecked(true);
                ConfigButtons.buttons.get(WhichButton.CENTIMETERS.ordinal()).setChecked(false);
            }
        });
    }

    /**
     * Checks the type of the measurement the user
     * wants to use for weight.
     */
    private void setupWeightButtons() {
        ConfigButtons.buttons.get(WhichButton.KILOGRAMS.ordinal()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConfigButtons.buttons.get(WhichButton.POUNDS.ordinal()).isChecked() == true)
                {
                    weightType = WhichButton.KILOGRAMS;
                    ConfigButtons.buttons.get(WhichButton.POUNDS.ordinal()).setChecked(false);
                    ConfigButtons.buttons.get(WhichButton.KILOGRAMS.ordinal()).setChecked(true);
                } else {
                    weightType = WhichButton.POUNDS;
                    ConfigButtons.buttons.get(WhichButton.POUNDS.ordinal()).setChecked(true);
                    ConfigButtons.buttons.get(WhichButton.KILOGRAMS.ordinal()).setChecked(false);
                }
            }
        });
        ConfigButtons.buttons.get(WhichButton.POUNDS.ordinal()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConfigButtons.buttons.get(WhichButton.KILOGRAMS.ordinal()).isChecked() == true) {
                    weightType = WhichButton.POUNDS;
                    ConfigButtons.buttons.get(WhichButton.KILOGRAMS.ordinal()).setChecked(false);
                    ConfigButtons.buttons.get(WhichButton.POUNDS.ordinal()).setChecked(true);
                } else {
                    weightType = WhichButton.KILOGRAMS;
                    ConfigButtons.buttons.get(WhichButton.KILOGRAMS.ordinal()).setChecked(true);
                    ConfigButtons.buttons.get(WhichButton.POUNDS.ordinal()).setChecked(false);
                }
            }
        });
    }

    /**
     * Checks the height and weight before moving on to the next screen.
     */
    private int checkWeightAndHeight() {
        EditText rawHeightEdit = (EditText) findViewById(R.id.heightEntryText);
        EditText rawWeightEdit = (EditText) findViewById(R.id.weightEntryText);


        String rawWeight = rawWeightEdit.getText().toString();
        String rawHeight = rawHeightEdit.getText().toString();

        // Check if the height is a valid height.
        try {
            userInput.setUserHeight(Double.parseDouble(rawHeight));
        } catch (NumberFormatException e) {
            return ErrorType.INVALID_HEIGHT.ordinal();
        }

        try {
            userInput.setUserWeight(Double.parseDouble(rawWeight));
        } catch (NumberFormatException e) {
            return ErrorType.INVALID_WEIGHT.ordinal();
        }

        return ErrorType.VALID_STATUS.ordinal();
    }


    private double calculateBMI() {
        double weight = userInput.getUserWeight();
        double height = userInput.getUserHeight();


        if (heightType.equals(WhichButton.INCHES))
        {
            height *= INT_TO_CENT;
        }
        if (weightType.equals(WhichButton.POUNDS))
        {
            weight /= POUND_TO_KILO;
        }


        double bmi = ((weight / Math.pow(height, 2.0))) * TWO_DIGIT_BMI_CONVERSION;
        userInput.setResult(bmi);
        return userInput.getResult();
    }

    private void submitButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = checkWeightAndHeight();
                if (result == ErrorType.INVALID_HEIGHT.ordinal()) {
                    Toast.makeText(MainActivity.this, "Invalid Height", Toast.LENGTH_LONG).show();
                } else if (result == ErrorType.INVALID_WEIGHT.ordinal()) {
                    Toast.makeText(MainActivity.this, "Invalid Weight", Toast.LENGTH_LONG).show();
                } else {
                    System.out.println(calculateBMI());
                    // Move to next string
                    transferToResultsScreen();
                }
            }
        });
    }

    /**
     * Transfers to the ResultActivity screen
     * and passes the user's result as well.
     */
    private void transferToResultsScreen() {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("bmi_result", userInput.getResult());
        startActivity(intent);
    }
}