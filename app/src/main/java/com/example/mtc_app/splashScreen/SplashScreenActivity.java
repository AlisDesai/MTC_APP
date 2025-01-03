package com.example.mtc_app.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.mtc_app.R;
import com.example.mtc_app.admin.HomeFragmentAdmin;
import com.example.mtc_app.helpAndSupport.HelpAndSupportActivity;
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
                Intent intent = new Intent(SplashScreenActivity.this, HomeFragmentAdmin.class);
                startActivity(intent);
            }
        });

        btnCustomerRepresentative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current activity content with HomeFragmentAdmin
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, new HomeFragmentAdmin()); // Replace `fragmentContainer` with your actual container ID
//                transaction.addToBackStack(null);
                transaction.commit();
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