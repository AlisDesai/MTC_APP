package com.example.mtc_app.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;
import com.example.mtc_app.login.CustomerLoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private Button btnAdmin, btnCustomerRepresentative, btnCustomer, btnEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Initialize buttons
        btnAdmin = findViewById(R.id.btn_admin);
        btnCustomerRepresentative = findViewById(R.id.btn_customer_representative);
        btnCustomer = findViewById(R.id.btn_customer);
        btnEmployee = findViewById(R.id.btn_employee);

        // Set click listeners for each button
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Admin Login
                Intent intent = new Intent(SplashScreenActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
            }
        });

        btnCustomerRepresentative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Customer Representative Login
                Intent intent = new Intent(SplashScreenActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
            }
        });

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Customer Login
                Intent intent = new Intent(SplashScreenActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
            }
        });

        btnEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Employee Login
                Intent intent = new Intent(SplashScreenActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}