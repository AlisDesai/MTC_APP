package com.example.mtc_app.staff;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtc_app.R;

public class staff_detailed_page extends AppCompatActivity {

    private TextView customerName, contactInfo, phoneNo, testingItems, statusTesting;
    private Button generateReportButton, updateInfoButton, deleteInfoButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_detailed_page);

        // Initialize views
        customerName = findViewById(R.id.customerName);
        contactInfo = findViewById(R.id.contactInfo);
        phoneNo = findViewById(R.id.phoneNo);
        testingItems = findViewById(R.id.testsPerformed);
        statusTesting = findViewById(R.id.status);


        ImageView downloadingIcon = findViewById(R.id.downloadReport);
        // Set an OnClickListener to the downloading icon
        downloadingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Print the message when the icon is clicked
                Toast.makeText(staff_detailed_page.this, "Downloading File...", Toast.LENGTH_SHORT).show();
            }
        });

        updateInfoButton = findViewById(R.id.buttonUpdate);
        deleteInfoButton = findViewById(R.id.buttonDelete);

        // Simulate loading data (could be from a database or API)
        loadCustomerDetails();

        updateInfoButton.setOnClickListener(v -> {
            // Simulate updating customer information
            Toast.makeText(staff_detailed_page.this, "Updating Information...", Toast.LENGTH_SHORT).show();
        });

        deleteInfoButton.setOnClickListener(v -> {
            // Simulate deleting customer information
            Toast.makeText(staff_detailed_page.this, "Deleting Customer Information...", Toast.LENGTH_SHORT).show();
        });
    }

    // Simulate a method to load customer details (could be replaced with real data)
    private void loadCustomerDetails() {
        customerName.setText("Customer Name: John Doe");
        contactInfo.setText("Email: john.doe@example.com");
        phoneNo.setText("Phone No: +1 234 567 890");
        testingItems.setText("Testing Items: Soil pH, Moisture");
        statusTesting.setText("Status: In Progress");
    }
}