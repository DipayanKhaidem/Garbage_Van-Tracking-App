package com.example.bbmpvehicletrackingapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RoleSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        Button btnDriver = findViewById(R.id.btn_driver);
        Button btnUser = findViewById(R.id.btn_user);

        // Navigate to Driver Login
        btnDriver.setOnClickListener(v -> {
            Intent intent = new Intent(RoleSelectionActivity.this, DriverLoginActivity.class);
            startActivity(intent);
        });

        // Navigate to User Login
        btnUser.setOnClickListener(v -> {
            Intent intent = new Intent(RoleSelectionActivity.this, UserLoginActivity.class);
            startActivity(intent);
        });
    }
}