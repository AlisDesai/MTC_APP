package com.example.mtc_app.cr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;

public class RepresentativeHomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_representative_home);

        // Display welcome message only
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView welcomeText = findViewById(R.id.welcome_text);
        welcomeText.setText("Welcome to the Representative Section!");
    }
}
