package com.example.bbmpvehicletrackingapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserDashboardActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        // Initialize Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set up the BottomNavigationView listener
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Load the default fragment (e.g., HomeFragment) when the activity is created
        if (savedInstanceState == null) { // Ensure the fragment is only loaded once
            loadFragment(new HomeFragment());
        }
    }

    // Bottom Navigation Item Selected Listener
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                // Use if-else to handle menu item clicks
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.nav_settings) {
                    selectedFragment = new SettingsFragment();
                }

                // Load the selected fragment
                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }

                return true; // Return true to display the selected fragment
            };

    // Method to load fragments
    private void loadFragment(Fragment fragment) {
        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a FragmentTransaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Replace the current fragment in the container with the new fragment
        transaction.replace(R.id.fragment_container, fragment);

        // Commit the transaction
        transaction.commit();
    }
}