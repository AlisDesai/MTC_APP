package com.example.mtc_app.customer.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.mtc_app.R;
import com.example.mtc_app.customer.profile.EditProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.IOException;

public class CustomerProfileFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    private ImageView profileImage;
    private TextView usernameText, userHandleText, emailValueText, addressValueText, phoneValueText;
    private Button tryAgainButton, okButton, editProfileButton, logOutButton;
    private GridLayout buttonsLayout;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Uri imageUri;

    public CustomerProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_customer_profile_fragment, container, false);
        profileImage = view.findViewById(R.id.profile_image);
        tryAgainButton = view.findViewById(R.id.tryAgainButton);
        okButton = view.findViewById(R.id.okButton);
        editProfileButton = view.findViewById(R.id.edit_profile_button);
        logOutButton = view.findViewById(R.id.logOut);
        buttonsLayout = view.findViewById(R.id.buttonsLayout);

        usernameText = view.findViewById(R.id.username);
        userHandleText = view.findViewById(R.id.user_handle);
        emailValueText = view.findViewById(R.id.email_value);
        addressValueText = view.findViewById(R.id.address_value);
        phoneValueText = view.findViewById(R.id.phone_value);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        profileImage.setOnClickListener(v -> showImageOptions());
        tryAgainButton.setOnClickListener(v -> showImageOptions());
        okButton.setOnClickListener(v -> uploadImageToStorage());

        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditProfileActivity.class); // Use getActivity() to get context
            startActivity(intent);
        });



        logOutButton.setOnClickListener(v -> {
            auth.signOut();
            Toast.makeText(getActivity(), "Logged Out Successfully", Toast.LENGTH_SHORT).show();
            // Add redirect to login screen if required
        });

        return view;
    }

    private void showImageOptions() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent chooser = Intent.createChooser(pickPhotoIntent, "Select Option");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takePictureIntent});
        startActivityForResult(chooser, REQUEST_IMAGE_PICK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                updateProfileImage(imageBitmap);
            } else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                imageUri = data.getData();
                try {
                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                    updateProfileImage(imageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed to load image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void updateProfileImage(Bitmap imageBitmap) {
        if (imageBitmap != null) {
            profileImage.setImageBitmap(imageBitmap);
            buttonsLayout.setVisibility(View.VISIBLE);
        }
    }

    private void uploadImageToStorage() {
        if (imageUri != null) {
            String userEmail = auth.getCurrentUser().getEmail();
            if (userEmail != null) {
                StorageReference imageRef = storageReference.child("profile_images/" + userEmail + ".jpg");
                imageRef.putFile(imageUri)
                        .addOnSuccessListener(taskSnapshot -> imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            storeImageUrlInFirestore(uri.toString(), userEmail);
                        }))
                        .addOnFailureListener(e -> Toast.makeText(getActivity(), "Image upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            } else {
                Toast.makeText(getActivity(), "User email not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void storeImageUrlInFirestore(String imageUrl, String userEmail) {
        firestore.collection("users").whereEqualTo("email", userEmail).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        String userId = queryDocumentSnapshots.getDocuments().get(0).getId();
                        firestore.collection("users").document(userId)
                                .update("profileImageUrl", imageUrl)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(getActivity(), "Profile image uploaded successfully", Toast.LENGTH_SHORT).show();
                                    buttonsLayout.setVisibility(View.GONE);
                                })
                                .addOnFailureListener(e -> Toast.makeText(getActivity(), "Failed to update profile image URL: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                    } else {
                        Toast.makeText(getActivity(), "User not found in database", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(getActivity(), "Failed to fetch user data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
