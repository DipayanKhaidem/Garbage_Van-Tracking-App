package com.example.bbmpvehicletrackingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DriverLoginActivity extends AppCompatActivity {

    // Hardcoded credentials
    private static final String DRIVER_USERNAME = "driver";
    private static final String DRIVER_PASSWORD = "driver123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.equals(DRIVER_USERNAME) && password.equals(DRIVER_PASSWORD)) {
                // Navigate to Driver Dashboard
                Intent intent = new Intent(DriverLoginActivity.this, DriverDashboardActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}