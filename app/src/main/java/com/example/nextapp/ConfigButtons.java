package com.example.nextapp;

import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

/**
 * Checks which button was pressed.
 */
enum WhichButton {
    POUNDS,
    KILOGRAMS,
    INCHES,
    CENTIMETERS
};

/**
 * Handles all the buttons.
 */
public class ConfigButtons {
    public static ArrayList<ToggleButton> buttons;

    public ConfigButtons() {
        buttons = new ArrayList<ToggleButton>();
    }

    public void addButton(ToggleButton button) {
        buttons.add(button);
    }

}
