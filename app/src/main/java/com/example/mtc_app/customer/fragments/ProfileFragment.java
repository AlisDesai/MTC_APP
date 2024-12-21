package com.example.mtc_app.customer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mtc_app.R;

public class ProfileFragment extends Fragment {
    private ImageView profilePicture;
    private TextView profileName, profileEmail, profilePhone;
    private Button btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.activity_profile_fragment, container, false);

        // Initialize views using the inflated view
        profilePicture = view.findViewById(R.id.profileImage);
        profileName = view.findViewById(R.id.userName);
        profileEmail = view.findViewById(R.id.emailValue);
        profilePhone = view.findViewById(R.id.phoneValue);
        btnLogout = view.findViewById(R.id.btnLogout);

        // Simulate loading profile data (replace with real data)
        loadProfileData();

        // Set logout button click listener
        btnLogout.setOnClickListener(v -> {
            // Simulate logout action (replace with actual logout logic)
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
            // Add actual logout logic here (like clearing session or navigating to login screen)
        });

        return view;
    }

    // Simulate method to load and update profile data dynamically
    private void loadProfileData() {
        // Example data (replace with actual data from a database or API)
        profilePicture.setImageResource(R.drawable.cust_profile); // Set a profile picture
        profileName.setText("Alis Desai");                      // Set the profile name
        profileEmail.setText("aliskdesai111@gmail.com");       // Set the profile email
        profilePhone.setText("9016071000");                    // Set the profile phone number
    }
}
