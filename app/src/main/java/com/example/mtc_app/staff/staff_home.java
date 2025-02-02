package com.example.mtc_app.staff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtc_app.R;
import com.example.mtc_app.staff.adapter.ItemData;
import com.example.mtc_app.staff.adapter.adapter_home;

import java.util.ArrayList;
import java.util.List;

public class staff_home extends AppCompatActivity {

    private RecyclerView homeRecyclerView;
    private EditText searchBar;
    private ImageView filterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);

        // Profile Icon Click Listener
        ImageView profileIcon = findViewById(R.id.profileIcon);
        profileIcon.setOnClickListener(v -> {
            // Navigate to profile page
            Intent intent = new Intent(staff_home.this, staff_profile_page.class);
            startActivity(intent);
        });

        // Initialize Search Bar
        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            String query = searchBar.getText().toString().trim();
            if (!query.isEmpty()) {
                Toast.makeText(staff_home.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
                // Implement your search logic here
            } else {
                Toast.makeText(staff_home.this, "Enter a search term", Toast.LENGTH_SHORT).show();
            }
            return true;
        });

        // Initialize Filter Button
        filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(v -> {
            // Implement your filter options here
            Toast.makeText(staff_home.this, "Filter options clicked", Toast.LENGTH_SHORT).show();
        });

        // Initialize RecyclerView
        homeRecyclerView = findViewById(R.id.homeRecyclerView);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Prepare list of data items
        List<ItemData> dataItems = new ArrayList<>();
        dataItems.add(new ItemData("Soil Analysis", "Analyze soil health and quality", R.drawable.ic_notifications));
        dataItems.add(new ItemData("Crop Health", "Monitor and manage crop health", R.drawable.ic_notifications));
        dataItems.add(new ItemData("Water Management", "Track water usage for optimal farming", R.drawable.ic_notifications));

        // Set up the RecyclerView with an adapter
        adapter_home homeAdapter = new adapter_home(dataItems, this);
        homeRecyclerView.setAdapter(homeAdapter);

        // Handle item click events in RecyclerView
        homeAdapter.setOnItemClickListener(position -> {
            // Redirect to the Detailed Page with selected item data
            ItemData selectedItem = dataItems.get(position);
            Intent intent = new Intent(staff_home.this, staff_detailed_page.class);
            intent.putExtra("itemName", selectedItem.getTitle());
            startActivity(intent);
        });
    }
}
