package com.example.mtc_app.customer.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mtc_app.R;
import com.example.mtc_app.customer.CustomerHomePageActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends AppCompatActivity {
    private EditText emailEditText, addressEditText, phoneEditText;
    private Button saveButton;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        emailEditText = findViewById(R.id.edit_email);
        addressEditText = findViewById(R.id.edit_address);
        phoneEditText = findViewById(R.id.edit_phone);
        saveButton = findViewById(R.id.save_button);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        saveButton.setOnClickListener(v -> saveProfileUpdates());
    }

    private void saveProfileUpdates() {
        String email = emailEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if (!email.isEmpty() && !address.isEmpty() && !phone.isEmpty()) {
            String userId = auth.getCurrentUser().getUid();
            firestore.collection("users").document(userId)
                    .update("email", email, "address", address, "phone", phone)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(EditProfileActivity.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfileActivity.this, CustomerHomePageActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(EditProfileActivity.this, "Failed to update profile: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(EditProfileActivity.this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
        }
    }
}
