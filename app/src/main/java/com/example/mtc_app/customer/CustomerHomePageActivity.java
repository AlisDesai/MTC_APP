package com.example.mtc_app.customer;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.mtc_app.R;
import com.example.mtc_app.customer.fragments.CustomerHomeFragment;
import com.example.mtc_app.customer.fragments.CustomerProfileFragment;
import com.example.mtc_app.customer.fragments.MakeOrderFragment;

public class CustomerHomePageActivity extends AppCompatActivity {

    private ImageView backArrow;
    private TextView pageTitle;
    private FrameLayout loadingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home_page);

        // Initialize the back arrow and page title
        backArrow = findViewById(R.id.backArrow);
        pageTitle = findViewById(R.id.pageTitle);
        loadingContainer = findViewById(R.id.loadingContainer);

        // Set up the back arrow click listener
        backArrow.setOnClickListener(view -> finish()); // Finish the current activity and go back

        // Set default fragment (HomeFragment)
        setFragment(new CustomerHomeFragment(), "Home Page");

        // Navigation bar items click listeners
        findViewById(R.id.nav_home).setOnClickListener(v -> setFragment(new CustomerHomeFragment(), "Home Page"));

        // Add the progress bar functionality for Make Order button
        findViewById(R.id.nav_request_order).setOnClickListener(v -> {
            showProgressBar(); // Show the progress bar before switching fragment
            setFragment(new MakeOrderFragment(), "Make Orders");
        });

        findViewById(R.id.nav_profile).setOnClickListener(v -> setFragment(new CustomerProfileFragment(), "Profile"));
    }

    private void setFragment(Fragment fragment, String title) {
        // Update the page title
        pageTitle.setText(title);

        // Show the progress bar for the Make Order Fragment
        if (fragment instanceof MakeOrderFragment) {
            showProgressBar();
        }

        // Replace the fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    // Show the progress bar
    private void showProgressBar() {
        loadingContainer.setVisibility(FrameLayout.VISIBLE);
    }

    // Hide the progress bar
    public void hideProgressBar() {
        loadingContainer.setVisibility(FrameLayout.GONE);
    }
}
