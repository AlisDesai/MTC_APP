package com.example.mtc_app.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mtc_app.R;
import com.example.mtc_app.order.MyOrdersActivity;
import com.example.mtc_app.order.RequestOrderActivity;

public class CustomerHomePageActivity extends AppCompatActivity {

    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home_page);

        // Example of setting customer name (can be dynamic)
        welcomeText = findViewById(R.id.welcomeText);
        String customerName = "John Doe"; // You can fetch this from intent or shared preferences
        welcomeText.setText("Welcome, " + customerName);

        findViewById(R.id.nav_home).setOnClickListener(v -> {
            // Redirect to Home Page (optional)
        });

        findViewById(R.id.nav_request_order).setOnClickListener(v -> {
            Intent intent = new Intent(CustomerHomePageActivity.this, RequestOrderActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.nav_my_orders).setOnClickListener(v -> {
            Intent intent = new Intent(CustomerHomePageActivity.this, MyOrdersActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.nav_profile).setOnClickListener(v -> {
            Intent intent = new Intent(CustomerHomePageActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }
}
