package com.example.mtc_app.customer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mtc_app.R;
import com.example.mtc_app.customer.profile.EditProfileActivity;
import com.example.mtc_app.login.CustomerLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomerProfileFragment extends Fragment {

    private TextView usernameText, userHandleText, emailValueText, addressValueText, phoneValueText;
    private Button editProfileButton, logOutButton;
    private ProgressBar loadingProgress;

    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

    public CustomerProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_customer_profile_fragment, container, false);

        // Initialize UI components
        usernameText = view.findViewById(R.id.username);
        userHandleText = view.findViewById(R.id.user_handle);
        emailValueText = view.findViewById(R.id.email_value);
        addressValueText = view.findViewById(R.id.address_value);
        phoneValueText = view.findViewById(R.id.phone_value);
        editProfileButton = view.findViewById(R.id.edit_profile_button);
        logOutButton = view.findViewById(R.id.logOut);
        loadingProgress = view.findViewById(R.id.loading_progress);

        // Initialize Firebase
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        // Load user details immediately
        loadUserDetails();

        // Edit Profile button click listener
        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditProfileActivity.class);
            startActivity(intent);
        });

        // Log Out button click listener
        logOutButton.setOnClickListener(v -> {
            auth.signOut();
            Intent intent = new Intent(getActivity(), CustomerLoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return view;
    }

    private void loadUserDetails() {
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            // Show the loading progress
            loadingProgress.setVisibility(View.VISIBLE);

            String userId = currentUser.getUid();
            firestore.collection("users").document(userId)
                    .get()
                    .addOnSuccessListener(this::populateUserDetails)
                    .addOnFailureListener(e -> {
                        loadingProgress.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Failed to load user details: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            loadingProgress.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "No user logged in", Toast.LENGTH_SHORT).show();
        }
    }

    private void populateUserDetails(DocumentSnapshot document) {
        if (document.exists()) {
            usernameText.setText(document.getString("name"));
            userHandleText.setText(document.getString("role"));
            emailValueText.setText(document.getString("email"));
            addressValueText.setText(document.getString("address"));
            phoneValueText.setText(document.getString("phone"));
        } else {
            Toast.makeText(getActivity(), "User data not found", Toast.LENGTH_SHORT).show();
        }

        // Hide the loading progress once the data is populated
        loadingProgress.setVisibility(View.GONE);
    }
}
