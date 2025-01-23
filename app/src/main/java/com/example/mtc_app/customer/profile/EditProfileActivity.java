package com.example.mtc_app.customer.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    private EditText emailEditText, addressEditText, phoneEditText;
    private Button saveButton, tryAgainButton, okButton;
    private ImageView profileImage;
    private View buttonsLayout;

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private StorageReference storageReference;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        emailEditText = findViewById(R.id.edit_email);
        addressEditText = findViewById(R.id.edit_address);
        phoneEditText = findViewById(R.id.edit_phone);
        saveButton = findViewById(R.id.save_button);
        tryAgainButton = findViewById(R.id.tryAgainButton);
        okButton = findViewById(R.id.okButton);
        profileImage = findViewById(R.id.profile_image);
        buttonsLayout = findViewById(R.id.buttonsLayout);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        fetchData();

        profileImage.setOnClickListener(v -> showImageOptions());
        tryAgainButton.setOnClickListener(v -> showImageOptions());
        okButton.setOnClickListener(v -> uploadImageToStorage());

        saveButton.setOnClickListener(v -> {
            String newEmail = emailEditText.getText().toString().trim();
            String newAddress = addressEditText.getText().toString().trim();
            String newPhone = phoneEditText.getText().toString().trim();

            String userId = auth.getCurrentUser().getUid();

            if (!newEmail.isEmpty()) {
                updateEmail(newEmail, newAddress, newPhone);
            } else {
                updateProfile(userId, newAddress, newPhone);
            }
        });
    }

    private void fetchData() {
        String userId = auth.getCurrentUser().getUid();
        firestore.collection("users").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        populateUserData(documentSnapshot);
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to fetch data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void populateUserData(DocumentSnapshot documentSnapshot) {
        String email = documentSnapshot.getString("email");
        String address = documentSnapshot.getString("address");
        String phone = documentSnapshot.getString("phone");
        String imageUrl = documentSnapshot.getString("image");

        emailEditText.setText(email);
        addressEditText.setText(address);
        phoneEditText.setText(phone);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            // Load the image using a library like Glide or Picasso
            // Glide.with(this).load(imageUrl).into(profileImage);
        }
    }

    private void showImageOptions() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent chooser = Intent.createChooser(pickPhotoIntent, "Select Option");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takePictureIntent});
        startActivityForResult(chooser, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                updateProfileImage(imageBitmap, null);
            } else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                imageUri = data.getData();
                try {
                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    updateProfileImage(imageBitmap, imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void updateProfileImage(Bitmap imageBitmap, Uri uri) {
        if (imageBitmap != null) {
            profileImage.setImageBitmap(imageBitmap);
            buttonsLayout.setVisibility(View.VISIBLE);
            imageUri = uri;
        }
    }

    private void uploadImageToStorage() {
        if (imageUri != null) {
            String userId = auth.getCurrentUser().getUid();
            StorageReference imageRef = storageReference.child("profile_images/" + userId + ".jpg");

            imageRef.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        storeImageUrlInFirestore(uri.toString());
                    }))
                    .addOnFailureListener(e -> Toast.makeText(this, "Image upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void storeImageUrlInFirestore(String imageUrl) {
        String userId = auth.getCurrentUser().getUid();
        firestore.collection("users").document(userId)
                .update("image", imageUrl)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Image updated successfully!", Toast.LENGTH_SHORT).show();
                    buttonsLayout.setVisibility(View.GONE);
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to update image URL: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void updateEmail(String newEmail, String newAddress, String newPhone) {
        auth.getCurrentUser().updateEmail(newEmail)
                .addOnSuccessListener(aVoid -> {
                    String userId = auth.getCurrentUser().getUid();
                    firestore.collection("users").document(userId)
                            .update("email", newEmail, "address", newAddress, "phone", newPhone)
                            .addOnSuccessListener(aVoid1 -> {
                                Toast.makeText(this, "Email and profile updated successfully!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Failed to update email in Firestore: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to update email in Firebase Authentication: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void updateProfile(String userId, String newAddress, String newPhone) {
        firestore.collection("users").document(userId)
                .update("address", newAddress, "phone", newPhone)
                .addOnSuccessListener(aVoid -> Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to update profile: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
