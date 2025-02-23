package com.example.bbmpvehicletrackingapp;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Set the splash screen layout

        // Delay for 5 seconds (5000 milliseconds)
        new Handler().postDelayed(() -> {
            // Navigate to the RoleSelectionActivity after 5 seconds
            Intent intent = new Intent(SplashActivity.this, RoleSelectionActivity.class);
            startActivity(intent);
            finish(); // Close the splash activity so the user can't go back to it
        }, 5000);
    }
}