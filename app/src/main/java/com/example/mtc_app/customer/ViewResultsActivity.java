package com.example.mtc_app.customer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;

public class ViewResultsActivity extends AppCompatActivity {

    private TextView resultsTextView;
    private Button refreshButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);

        resultsTextView = findViewById(R.id.resultsTextView);
        refreshButton = findViewById(R.id.refreshButton);

        // Sample results
        resultsTextView.setText("Test results will be displayed here.");

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic to fetch and update the results (e.g., from Firebase)
                resultsTextView.setText("Updated test results...");
            }
        });
    }
}
