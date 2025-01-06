package com.example.mtc_app.customer;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.mtc_app.R;
import com.example.mtc_app.customer.fragments.CustomerHomeFragment;
import com.example.mtc_app.customer.fragments.CustomerProfileFragment;
import com.example.mtc_app.customer.fragments.MakeOrderFragment;

public class CustomerHomePageActivity extends AppCompatActivity {

    private ImageView backArrow;
    private TextView pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home_page);

        // Initialize the back arrow and page title
        backArrow = findViewById(R.id.backArrow);
        pageTitle = findViewById(R.id.pageTitle);

        // Set up the back arrow click listener
        backArrow.setOnClickListener(view -> finish()); // Finish the current activity and go back

        // Set default fragment (HomeFragment)
        setFragment(new CustomerHomeFragment(), "Home Page");

        // Navigation bar items click listeners
        findViewById(R.id.nav_home).setOnClickListener(v -> setFragment(new CustomerHomeFragment(), "Home Page"));
        findViewById(R.id.nav_request_order).setOnClickListener(v -> setFragment(new MakeOrderFragment(), "Make Orders"));
//        findViewById(R.id.nav_my_orders).setOnClickListener(v -> setFragment(new MyOrdersFragment(), "My Orders"));
        findViewById(R.id.nav_profile).setOnClickListener(v -> setFragment(new CustomerProfileFragment(), "Profile"));
    }

    private void setFragment(Fragment fragment, String title) {
        // Update the page title
        pageTitle.setText(title);

        // Replace the fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}