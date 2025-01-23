package com.example.mtc_app.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.mtc_app.R;
import com.example.mtc_app.admin.fragments.AdminCRFragment;
import com.example.mtc_app.admin.fragments.AdminHomeFragment;
import com.example.mtc_app.admin.fragments.AdminStaffFragment;
import com.example.mtc_app.login.CustomerLoginActivity;
import com.google.android.material.navigation.NavigationView;

public class AdminHomePageActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private CardView customer1, customer2;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TextView menuItem1, menuItem2, menuItem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        customer1 = findViewById(R.id.Customer1);
        customer2 = findViewById(R.id.Customer2);

        // Open Order Details when card views are clicked
        customer1.setOnClickListener(view -> openOrderDetail("Order 1", "John Doe", "123 Main St", "9876543210", "john@example.com"));
        customer2.setOnClickListener(view -> openOrderDetail("Order 2", "Jane Smith", "456 Market St", "9876543211", "jane@example.com"));

        // Setup Drawer Layout and Navigation View
        drawerLayout = findViewById(R.id.drawer_layout);

        // Setup NavigationView Listener for TextView clicks
        NavigationView navigationView = findViewById(R.id.nav_view);
        menuItem1 = navigationView.findViewById(R.id.menuItem1);
        menuItem2 = navigationView.findViewById(R.id.menuItem2);
        menuItem3 = navigationView.findViewById(R.id.menuItem3);

        menuItem1.setOnClickListener(v -> {
            handleMenuItemClick("Home Page");
            drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer when a menu item is clicked
        });

        menuItem2.setOnClickListener(v -> {
            handleMenuItemClick("CR Section");
            drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer when a menu item is clicked
        });

        menuItem3.setOnClickListener(v -> {
            handleMenuItemClick("Staff Section");
            drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer when a menu item is clicked
        });

        // Add Navigation Drawer Toggle
        androidx.appcompat.app.ActionBarDrawerToggle toggle = new androidx.appcompat.app.ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Setup NavigationView Item Selected Listener
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_logout) {
                handleLogoutClick();
            }
            drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer when a navigation item is selected
            return true;
        });

        // Initially, load the home fragment
        loadFragment(new AdminHomeFragment(), false);
    }

    private void openOrderDetail(String order, String name, String address, String phone, String email) {
        Intent intent = new Intent(AdminHomePageActivity.this, AdminOrderDetail.class);
        intent.putExtra("order", order);
        intent.putExtra("name", name);
        intent.putExtra("address", address);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    private void handleMenuItemClick(String section) {
        switch (section) {
            case "CR Section":
                loadFragment(new AdminCRFragment(), false);
                break;
            case "Staff Section":
                loadFragment(new AdminStaffFragment(), false);
                break;
            case "Home Page":
                loadFragment(new AdminHomeFragment(), false); // Or add relevant fragment
                break;
        }
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);  // Add to back stack when navigating to home
        }
        transaction.commit();
    }


    private void handleLogoutClick() {
        startActivity(new Intent(this, CustomerLoginActivity.class));
        finish(); // Close Current Activity
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
