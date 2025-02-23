package com.example.bbmpvehicletrackingapp;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class ChangePasswordFragment extends Fragment {

    private EditText etCurrentPassword, etNewPassword, etConfirmPassword;
    private Button btnSavePassword;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        // Initialize views
        etCurrentPassword = view.findViewById(R.id.et_current_password);
        etNewPassword = view.findViewById(R.id.et_new_password);
        etConfirmPassword = view.findViewById(R.id.et_confirm_password);
        btnSavePassword = view.findViewById(R.id.btn_save_password);

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", 0);

        // Save Password Button Click Listener
        btnSavePassword.setOnClickListener(v -> {
            String currentPassword = etCurrentPassword.getText().toString().trim();
            String newPassword = etNewPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            // Retrieve stored password
            String storedPassword = sharedPreferences.getString("password", "user123");

            // Validate current password
            if (!currentPassword.equals(storedPassword)) {
                Toast.makeText(getContext(), "Current password is incorrect", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate new password and confirm password
            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(getContext(), "New passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Update password in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("password", newPassword);
            editor.apply();

            Toast.makeText(getContext(), "Password changed successfully", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}