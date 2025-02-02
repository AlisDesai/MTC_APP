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
            orderStatus.setText("Status: " + intent.getStringExtra("orderStatus"));
            dispatchMode.setText("Dispatch Mode: " + intent.getStringExtra("dispatchMode"));
            orderDate.setText("Order Date: " + intent.getStringExtra("orderDate"));
            segment.setText("Segment: " + intent.getStringExtra("segment"));
            price.setText("Price: " + intent.getStringExtra("price"));
        }

        backButton.setOnClickListener(v -> finish());
    }
}
