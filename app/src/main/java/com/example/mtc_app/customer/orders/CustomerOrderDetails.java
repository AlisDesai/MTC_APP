package com.example.mtc_app.customer.orders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;
import com.google.android.material.button.MaterialButton;

public class CustomerOrderDetails extends AppCompatActivity {

    private TextView orderStatus, dispatchMode, orderDate, segment, price;
    private MaterialButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_details);

        orderStatus = findViewById(R.id.orderStatus);
        dispatchMode = findViewById(R.id.dispatchMode);
        orderDate = findViewById(R.id.orderDate);
        segment = findViewById(R.id.segment);
        price = findViewById(R.id.price);
        backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        if (intent != null) {
            // ✅ Check for null values and set default values if missing
            String status = intent.getStringExtra("orderStatus") != null ? intent.getStringExtra("orderStatus") : "Unknown";
            String dispatch = intent.getStringExtra("dispatchMode") != null ? intent.getStringExtra("dispatchMode") : "Not Available";
            String date = intent.getStringExtra("orderDate") != null ? intent.getStringExtra("orderDate") : "N/A";
            String seg = intent.getStringExtra("segment") != null ? intent.getStringExtra("segment") : "Not Specified";
            String priceValue = intent.getStringExtra("price") != null ? intent.getStringExtra("price") : "0";

            orderStatus.setText("Status: " + status);
            dispatchMode.setText("Dispatch Mode: " + dispatch);
            orderDate.setText("Order Date: " + date);
            segment.setText("Segment: " + seg);
            price.setText("Price: " + priceValue);
        }

        backButton.setOnClickListener(v -> finish());
    }
}


