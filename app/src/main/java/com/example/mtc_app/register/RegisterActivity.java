//package com.example.mtc_app.register;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.mtc_app.R;
//import com.example.mtc_app.login.CustomerLoginActivity;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//
//public class RegisterActivity extends AppCompatActivity {
//
//    private EditText nameField, emailField, passwordField, phoneField, roleField;
//    private Button registerButton;
//    private ProgressBar progressBar;
//    private FirebaseAuth auth;
//    private FirebaseFirestore firestore;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        nameField = findViewById(R.id.nameField);
//        emailField = findViewById(R.id.emailField);
//        passwordField = findViewById(R.id.passwordField);
//        phoneField = findViewById(R.id.phoneField);
//        roleField = findViewById(R.id.roleField);
//        registerButton = findViewById(R.id.registerButton);
//        progressBar = findViewById(R.id.progressBar);
//
//        TextView redirectToLogin = findViewById(R.id.tv_redirect_to_login);
//        redirectToLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Redirect to LoginActivity
//                Intent intent = new Intent(RegisterActivity.this, CustomerLoginActivity.class);
//                startActivity(intent);
//                finish(); // Optionally finish RegisterActivity to prevent back navigation to it
//            }
//        });
//
//        auth = FirebaseAuth.getInstance();
//        firestore = FirebaseFirestore.getInstance();
//
//        registerButton.setOnClickListener(view -> registerUser());
//    }
//
//    private void registerUser() {
//        String name = nameField.getText().toString();
//        String email = emailField.getText().toString();
//        String password = passwordField.getText().toString();
//        String phone = phoneField.getText().toString();
//        String role = roleField.getText().toString();
//
//        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(role)) {
//            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        progressBar.setVisibility(View.VISIBLE);
//
//        auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(task -> {
//                    progressBar.setVisibility(View.GONE);
//                    if (task.isSuccessful()) {
//                        String userId = auth.getCurrentUser().getUid();
//                        saveUserDetailsToFirestore(userId, name, email, phone, role);
//                    } else {
//                        Toast.makeText(this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    private void saveUserDetailsToFirestore(String userId, String name, String email, String phone, String role) {
//        // Get current date and time
//        String createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
//
//        // Create user data map
//        Map<String, Object> user = new HashMap<>();
//        user.put("name", name);
//        user.put("email", email);
//        user.put("phone", phone);
//        user.put("role", role);
//        user.put("created_at", createdAt);
//
//        firestore.collection("users").document(userId)
//                .set(user)
//                .addOnSuccessListener(aVoid -> {
//                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(RegisterActivity.this, CustomerLoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                })
//                .addOnFailureListener(e -> Toast.makeText(this, "Error saving user: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//    }
//}


package com.example.mtc_app.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;
import com.example.mtc_app.login.CustomerLoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameField, emailField, passwordField, phoneField, roleField;
    private Button registerButton;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameField = findViewById(R.id.nameField);
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        phoneField = findViewById(R.id.phoneField);
        roleField = findViewById(R.id.roleField);
        registerButton = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar);

        TextView redirectToLogin = findViewById(R.id.tv_redirect_to_login);
        redirectToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to LoginActivity
                Intent intent = new Intent(RegisterActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
                finish(); // Optionally finish RegisterActivity to prevent back navigation to it
            }
        });

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        registerButton.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String phone = phoneField.getText().toString();
        String role = roleField.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(role)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            updateUserDisplayName(user, name);
                        }
                    } else {
                        Toast.makeText(this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUserDisplayName(FirebaseUser user, String name) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        saveUserDetailsToFirestore(user.getUid(), name, user.getEmail());
                    } else {
                        Toast.makeText(this, "Failed to update user profile: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserDetailsToFirestore(String userId, String name, String email) {
        String phone = phoneField.getText().toString();
        String role = roleField.getText().toString();
        String createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("phone", phone);
        user.put("role", role);
        user.put("created_at", createdAt);

        firestore.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, CustomerLoginActivity.class);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error saving user: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

}
