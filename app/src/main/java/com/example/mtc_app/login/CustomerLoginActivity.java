// LoginActivity.java
package com.example.mtc_app.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;
import com.example.mtc_app.customer.*;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerLoginActivity extends AppCompatActivity {

    private EditText emailField, passwordField;
    private Button loginButton, registerButton;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(view -> loginUser());

        // Redirect to RegisterActivity when registerButton is clicked
        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(CustomerLoginActivity.this, CustomerHomePageActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CustomerLoginActivity.this, CustomerHomePageActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
