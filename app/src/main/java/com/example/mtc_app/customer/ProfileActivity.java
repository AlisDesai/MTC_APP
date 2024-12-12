package com.example.mtc_app.customer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileTextView;
    private EditText nameEditText, emailEditText;
    private Button saveButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileTextView = findViewById(R.id.profileTextView);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        saveButton = findViewById(R.id.saveButton);

        // Sample user profile data
        profileTextView.setText("Profile Details:");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic to save profile changes (e.g., to Firebase)
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();

                // Example: Save to Firebase Firestore or update UI with new info
            }
        });
    }
}
