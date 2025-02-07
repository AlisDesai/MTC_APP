package com.example.mtc_app.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.mtc_app.login.CustomerLoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AuthUtils {

    public static void logout(Context context) {
        // Clear user login state from SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false); // Explicitly mark user as logged out
        editor.remove("userRole");             // Remove stored user role
        editor.apply();                        // Apply changes

        // Sign out from Firebase
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();

        // Show a logout success message
        Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show();

        // Redirect to Login Screen
        Intent intent = new Intent(context, CustomerLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear activity stack
        context.startActivity(intent);
    }
}
