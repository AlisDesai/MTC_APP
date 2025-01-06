package com.example.mtc_app.staff;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;

public class staff_profile_page extends AppCompatActivity {

    private ImageView profilePicture;
    private TextView profileName, profileEmail, profilePhone;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_profile_page);

        // Initialize views
        profilePicture = findViewById(R.id.profileImage);
        profileName = findViewById(R.id.userName);
        profileEmail = findViewById(R.id.emailValue);
        profilePhone = findViewById(R.id.phoneValue);
        btnLogout = findViewById(R.id.btnLogout);

        // Simulate loading profile data (replace with real data)
        loadProfileData();

        // Set logout button click listener
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simulate logout action (replace with actual logout logic)
                Toast.makeText(staff_profile_page.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                // You can add actual logout logic here (like clearing session or navigating to login screen)
            }
        });
    }

    // Simulate method to load and update profile data dynamically
    private void loadProfileData() {
        // Example data (replace with actual data from a database or API)
        profilePicture.setImageResource(R.drawable.cust_profile); // Set a profile picture
        profileName.setText("Amir Asadi");                      // Set the profile name
        profileEmail.setText("mail.amirasadi@gmail.com");       // Set the profile email
        profilePhone.setText("0911***4504");                    // Set the profile phone number
    }
}
