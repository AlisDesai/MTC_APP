package com.example.mtc_app.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mtc_app.R;


public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Navigate to add materials for testing
        findViewById(R.id.addMaterialsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, AddMaterialsActivity.class);
                startActivity(intent);
            }
        });

        // Navigate to view test results
        findViewById(R.id.viewResultsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ViewResultsActivity.class);
                startActivity(intent);
            }
        });

        // Navigate to profile settings
        findViewById(R.id.profileButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
