package com.example.nextapp;

import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

enum WhichButton {
    POUNDS,
    KILOGRAMS,
    INCHES,
    CENTIMETERS,
    RESULTS
};

public class ConfigButtons {
    public static ArrayList<AppCompatButton> buttons;

    public ConfigButtons() {
        buttons = new ArrayList<>();
    }

    static void addButton(AppCompatButton button) {
        buttons.add(button);
    }

}
