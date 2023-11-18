package com.example.nextapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout topBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topBar = findViewById(R.id.topBar);
        initializeButtons();
    }

    private void initializeButtons() {
        ConfigButtons.addButton(findViewById(R.id.poundsButton));
        ConfigButtons.addButton(findViewById(R.id.kilogramsButton));
        ConfigButtons.addButton(findViewById(R.id.inchesButton));
        ConfigButtons.addButton(findViewById(R.id.centimetersButton));
        ConfigButtons.addButton(findViewById(R.id.resultsButton));
    }

    private void setupHeightButtons() {
        ConfigButtons.buttons.get(WhichButton.INCHES.ordinal()).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Handle On Clicked
            }
        });

        ConfigButtons.buttons.get(WhichButton.CENTIMETERS.ordinal()).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Handle On Clicked
            }
        });
    }
}