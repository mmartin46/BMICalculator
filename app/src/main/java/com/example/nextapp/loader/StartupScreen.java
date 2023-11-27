package com.example.nextapp.loader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

import com.example.nextapp.MainActivity;
import com.example.nextapp.R;

public class StartupScreen extends AppCompatActivity {
    private static final int SPLASH_WAIT_TIME = 2000;

    // Loads the screen until the main screen displays.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(StartupScreen.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_WAIT_TIME);
    }
}