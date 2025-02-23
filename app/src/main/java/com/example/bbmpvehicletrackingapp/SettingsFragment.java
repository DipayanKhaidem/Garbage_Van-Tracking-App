package com.example.bbmpvehicletrackingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private EditText etCurrentPassword, etNewPassword, etConfirmPassword;
    private Button btnChangePassword;
    private TextView tvStatus;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Initialize views
        etCurrentPassword = view.findViewById(R.id.et_current_password);
        etNewPassword = view.findViewById(R.id.et_new_password);
        etConfirmPassword = view.findViewById(R.id.et_confirm_password);
        btnChangePassword = view.findViewById(R.id.btn_change_password);
        tvStatus = view.findViewById(R.id.tv_status);

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        // Set up the Change Password button click listener
        btnChangePassword.setOnClickListener(v -> changePassword());

        return view;
    }

    private void changePassword() {
        String currentPassword = etCurrentPassword.getText().toString().trim();
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // Retrieve the stored password
        String storedPassword = sharedPreferences.getString("password", "");

        // Validate inputs
        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            showStatus("Please fill all fields", false);
            return;
        }

        if (!currentPassword.equals(storedPassword)) {
            showStatus("Current password is incorrect", false);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showStatus("New passwords do not match", false);
            return;
        }

        if (newPassword.length() < 6) {
            showStatus("Password must be at least 6 characters", false);
            return;
        }

        // Update the password in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", newPassword); // Update the password
        editor.apply();

        // Show success message
        showStatus("Password changed successfully", true);

        // Clear input fields
        etCurrentPassword.setText("");
        etNewPassword.setText("");
        etConfirmPassword.setText("");
    }

    private void showStatus(String message, boolean isSuccess) {
        tvStatus.setText(message);
        tvStatus.setVisibility(View.VISIBLE);
        tvStatus.setTextColor(isSuccess ? getResources().getColor(android.R.color.holo_green_dark) : getResources().getColor(android.R.color.holo_red_dark));

        // Hide the status message after 3 seconds
        tvStatus.postDelayed(() -> tvStatus.setVisibility(View.GONE), 3000);
    }
}