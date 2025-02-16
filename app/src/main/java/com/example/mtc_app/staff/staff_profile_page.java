package com.example.mtc_app.staff;

import com.example.mtc_app.profile.EditProfileActivity;
import com.example.mtc_app.utils.CloudinaryManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.mtc_app.R;
import com.example.mtc_app.auth.AuthUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class staff_profile_page extends AppCompatActivity {

    private static final String TAG = "StaffProfilePage";
    private ImageView profilePicture;
    private TextView profileName, profileEmail, profilePhone, addressValue;
    private Button btnLogout, editProfile;
    private FirebaseFirestore db;
    private Uri imageUri;
    private static final int GALLERY_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 101;

    // Cloudinary credentials
    private static final String CLOUDINARY_CLOUD_NAME = "dpqonar5z";  // Replace with your Cloudinary cloud name
    private static final String CLOUDINARY_UPLOAD_PRESET = "profile_pictures";  // Replace with your preset name
    private static final String CLOUDINARY_FOLDER_NAME = "profile_images"; // Folder name on Cloudinary

    // SharedPreferences constants
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "ProfileData";
    private static final String KEY_PROFILE_NAME = "profileName";
    private static final String KEY_PROFILE_EMAIL = "profileEmail";
    private static final String KEY_PROFILE_PHONE = "profilePhone";
    private static final String KEY_PROFILE_ADDRESS = "profileAddress";
    private static final String KEY_PROFILE_IMAGE_URL = "profileImageUrl";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_profile_page);

        db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        profilePicture = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.username);
        profileEmail = findViewById(R.id.emailValue);
        profilePhone = findViewById(R.id.phoneValue);
        addressValue = findViewById(R.id.addressValue);
        btnLogout = findViewById(R.id.btnLogout);
        editProfile = findViewById(R.id.edit_profile_button);

        if (isProfileDataAvailable()) {
            displayProfileData();
        } else if (currentUser != null) {
            String userId = currentUser.getUid();
            fetchProfileData(userId);
        } else {
            Toast.makeText(this, "User not logged in.", Toast.LENGTH_SHORT).show();
            finish();
        }

        ImageView editProfileIcon = findViewById(R.id.edit_icon);
        editProfileIcon.setOnClickListener(v -> showImagePickerDialog());

        btnLogout.setOnClickListener(v -> {
            clearLocalData();
            showLogoutConfirmation();
        });

        editProfile.setOnClickListener(v -> {
            Intent intent = new Intent(staff_profile_page.this, EditProfileActivity.class);
            startActivity(intent);
        });
    }

    private boolean isProfileDataAvailable() {
        return sharedPreferences.contains(KEY_PROFILE_NAME);
    }

    private void displayProfileData() {
        profileName.setText(sharedPreferences.getString(KEY_PROFILE_NAME, ""));
        profileEmail.setText(sharedPreferences.getString(KEY_PROFILE_EMAIL, ""));
        profilePhone.setText(sharedPreferences.getString(KEY_PROFILE_PHONE, ""));
        addressValue.setText(sharedPreferences.getString(KEY_PROFILE_ADDRESS, ""));

        String profileImageUrl = sharedPreferences.getString(KEY_PROFILE_IMAGE_URL, "");
        if (!profileImageUrl.isEmpty()) {
            Glide.with(this)
                    .load(profileImageUrl)
                    .placeholder(R.drawable.cust_profile)
                    .into(profilePicture);
        }
    }

    private void fetchProfileData(String userId) {
        db.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String name = documentSnapshot.getString("name");
                        String email = documentSnapshot.getString("email");
                        String phone = documentSnapshot.getString("phone");
                        String address = documentSnapshot.getString("address");
                        String profileImageUrl = documentSnapshot.getString("profileImageUrl");

                        // Save data to SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_PROFILE_NAME, name);
                        editor.putString(KEY_PROFILE_EMAIL, email);
                        editor.putString(KEY_PROFILE_PHONE, phone);
                        editor.putString(KEY_PROFILE_ADDRESS, address);
                        editor.putString(KEY_PROFILE_IMAGE_URL, profileImageUrl);
                        editor.apply();

                        displayProfileData();
                    } else {
                        Toast.makeText(this, "User not found.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error fetching profile data", e);
                    Toast.makeText(this, "Failed to load profile.", Toast.LENGTH_SHORT).show();
                });
    }

    private void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an option")
                .setItems(new String[]{"Gallery", "Camera"}, (dialog, which) -> {
                    if (which == 0) {
                        pickImageFromGallery();
                    } else {
                        captureImageFromCamera();
                    }
                })
                .show();
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    private void captureImageFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imageFile = new File(getExternalFilesDir(null), "profile_pic.jpg");
        imageUri = FileProvider.getUriForFile(this, "com.example.mtc_app.fileprovider", imageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_CODE && data != null) {
                imageUri = data.getData();
                uploadImageToCloudinary();
            } else if (requestCode == CAMERA_REQUEST_CODE) {
                uploadImageToCloudinary();
            }
        }
    }

    private void uploadImageToCloudinary() {
        if (imageUri == null) return;

        new Thread(() -> {
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);

                // Upload image to Cloudinary with folder path
                Map uploadParams = new HashMap<>();
                uploadParams.put("folder", CLOUDINARY_FOLDER_NAME); // Set the folder name

                // Upload the image
                Map uploadResult = CloudinaryManager.getInstance().uploader().upload(inputStream, uploadParams);

                String imageUrl = (String) uploadResult.get("secure_url");
                runOnUiThread(() -> updateProfileImageUrl(imageUrl));

            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(this, "Upload Failed", Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            }
        }).start();
    }

    private void updateProfileImageUrl(String imageUrl) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("users").document(userId)
                .update("profileImageUrl", imageUrl)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    Glide.with(this).load(imageUrl).into(profilePicture);

                    // Update local data
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PROFILE_IMAGE_URL, imageUrl);
                    editor.apply();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show());
    }

    private void showLogoutConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> logout())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void logout() {
        AuthUtils.logout(this);
        clearLocalData();
    }

    private void clearLocalData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Clears all stored data
        editor.apply();
    }
}
