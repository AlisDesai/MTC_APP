package com.example.mtc_app.customerRepresentative;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mtc_app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddCustomer extends Fragment {

    private TextInputEditText editTextName, editTextMobile, editTextEmail, editTextPassword, editTextRole;
    private Button addCustomerButton;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_customer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        editTextName = view.findViewById(R.id.editTextName);
        editTextMobile = view.findViewById(R.id.editTextMobile);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextRole = view.findViewById(R.id.editTextRole);
        addCustomerButton = view.findViewById(R.id.addCustomerButton);
        progressBar = view.findViewById(R.id.progressBar);

        // Initialize Firebase
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        // Set up button click listener
        addCustomerButton.setOnClickListener(v -> addCustomer());
    }

    private void addCustomer() {
        String name = editTextName.getText().toString();
        String mobile = editTextMobile.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String role = editTextRole.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(role)) {
            Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        // Register the customer in Firebase Authentication
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Get the newly created user's UID
                        String userId = auth.getCurrentUser().getUid();
                        saveCustomerToFirestore(userId, name, mobile, email, role);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveCustomerToFirestore(String userId, String name, String mobile, String email, String role) {
        // Create user data
        String createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        Map<String, Object> customer = new HashMap<>();
        customer.put("name", name);
        customer.put("mobile", mobile);
        customer.put("email", email);
        customer.put("role", role);
        customer.put("created_at", createdAt);

        // Save to Firestore
        firestore.collection("users").document(userId)
                .set(customer)
                .addOnSuccessListener(aVoid -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Customer added successfully", Toast.LENGTH_SHORT).show();

                    // Optional: Clear input fields after success
                    clearInputFields();
                })
                .addOnFailureListener(e -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Error saving customer: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void clearInputFields() {
        editTextName.setText("");
        editTextMobile.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
        editTextRole.setText("");
    }
}
