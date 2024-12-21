package com.example.mtc_app.helpAndSupport;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HelpAndSupportActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText queryEditText;
    private ImageView queryImageView, backArrow;
    private Button uploadImageButton, submitButton, emailButton, callButton;
    private ProgressBar progressBar;
    private Uri imageUri;

    private FirebaseStorage firebaseStorage;
    private FirebaseFirestore firestore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_support);

        // Initialize UI components
        queryEditText = findViewById(R.id.queryEditText);
        queryImageView = findViewById(R.id.queryImageView);
        uploadImageButton = findViewById(R.id.uploadImageButton);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progressBar);
        emailButton = findViewById(R.id.emailButton);
        callButton = findViewById(R.id.callButton);
        backArrow = findViewById(R.id.backArrow);

        // Initialize Firebase instances
        firebaseStorage = FirebaseStorage.getInstance();
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        // Set click listeners
        backArrow.setOnClickListener(view -> finish());
        uploadImageButton.setOnClickListener(view -> openImageChooser());
        submitButton.setOnClickListener(view -> submitQuery());
        emailButton.setOnClickListener(view -> sendEmail());
        callButton.setOnClickListener(view -> makeCall());

        backArrow.setOnClickListener(view -> {
            Toast.makeText(this, "Back arrow clicked", Toast.LENGTH_SHORT).show();
            finish();
        });

        uploadImageButton.setOnClickListener(view -> {
            Toast.makeText(this, "Upload button clicked", Toast.LENGTH_SHORT).show();
            openImageChooser();
        });

        submitButton.setOnClickListener(view -> {
            Toast.makeText(this, "Submit button clicked", Toast.LENGTH_SHORT).show();
            submitQuery();
        });

        emailButton.setOnClickListener(view -> {
            Toast.makeText(this, "Email button clicked", Toast.LENGTH_SHORT).show();
            sendEmail();
        });

        callButton.setOnClickListener(view -> {
            Toast.makeText(this, "Call button clicked", Toast.LENGTH_SHORT).show();
            makeCall();
        });

    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            queryImageView.setImageURI(imageUri);
        }
    }

    private void submitQuery() {
        String queryText = queryEditText.getText().toString().trim();

        if (queryText.isEmpty()) {
            Toast.makeText(this, "Please enter your query", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        if (imageUri != null) {
            uploadImageAndSaveQuery(queryText);
        } else {
            saveQueryToFirestore(queryText, null);
        }
    }

    private void uploadImageAndSaveQuery(String queryText) {
        StorageReference storageRef = firebaseStorage.getReference().child("query_images/" + System.currentTimeMillis() + ".jpg");

        storageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();
                    saveQueryToFirestore(queryText, imageUrl);
                }))
                .addOnFailureListener(e -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "Image upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void saveQueryToFirestore(String queryText, @Nullable String imageUrl) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            String userName = currentUser.getDisplayName();

            if (userName == null || userName.isEmpty()) {
                fetchUserNameFromFirestore(currentUser.getUid(), name -> saveQuery(queryText, imageUrl, name, userEmail));
            } else {
                saveQuery(queryText, imageUrl, userName, userEmail);
            }
        } else {
            saveQuery(queryText, imageUrl, "Anonymous", "anonymous@example.com");
        }
    }

    private void fetchUserNameFromFirestore(String userId, FirestoreCallback callback) {
        firestore.collection("users").document(userId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists() && documentSnapshot.contains("name")) {
                        callback.onCallback(documentSnapshot.getString("name"));
                    } else {
                        callback.onCallback("Anonymous");
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("HelpAndSupportActivity", "Failed to fetch user name: " + e.getMessage());
                    callback.onCallback("Anonymous");
                });
    }

    private void saveQuery(String queryText, @Nullable String imageUrl, String userName, String userEmail) {
        String formattedTimestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date());

        Map<String, Object> queryData = new HashMap<>();
        queryData.put("name", userName);
        queryData.put("email", userEmail);
        queryData.put("query", queryText);
        queryData.put("imageUrl", imageUrl);
        queryData.put("timestamp", formattedTimestamp);
        queryData.put("status", "pending");

        firestore.collection("customer_queries")
                .add(queryData)
                .addOnSuccessListener(documentReference -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "Query submitted successfully", Toast.LENGTH_SHORT).show();
                    clearQueryForm();
                })
                .addOnFailureListener(e -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "Query submission failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void clearQueryForm() {
        queryEditText.setText("");
        queryImageView.setImageResource(0); // Clears the image view
        imageUri = null;
    }

    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:mnswap3@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Help and Support Query");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
        } catch (Exception e) {
            Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeCall() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:+919016071000"));

        try {
            startActivity(callIntent);
        } catch (Exception e) {
            Toast.makeText(this, "Unable to make a call", Toast.LENGTH_SHORT).show();
        }
    }


    interface FirestoreCallback {
        void onCallback(String userName);
    }
}
