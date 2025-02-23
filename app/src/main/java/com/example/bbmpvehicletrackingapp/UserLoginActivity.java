package com.example.bbmpvehicletrackingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserLoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        // Initialize views
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_sign_up);

        // Set up the Login button click listener
        btnLogin.setOnClickListener(v -> loginUser());

        // Set up the Sign-Up button click listener
        btnSignUp.setOnClickListener(v -> {
            // Navigate to the SignUpActivity
            Intent intent = new Intent(UserLoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve the stored password
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String storedPassword = sharedPreferences.getString("password", "");

        // Check if the entered password matches the stored password
        if (password.equals(storedPassword)) {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

            // Clear input fields
            etUsername.setText("");
            etPassword.setText("");

            // Navigate to the dashboard
            Intent intent = new Intent(this, UserDashboardActivity.class);
            startActivity(intent);
            finish(); // Close the login activity
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }
}