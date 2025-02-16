package com.example.mtc_app.staff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtc_app.R;
import com.example.mtc_app.staff.adapter.adapter_home;
import com.example.mtc_app.staff.adapter.ItemData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class staff_home extends AppCompatActivity {

    private RecyclerView recyclerView;
    private adapter_home adapter;
    private List<ItemData> itemList = new ArrayList<>();
    private FirebaseFirestore db;
    private String staffCategory = ""; // Store staff category

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);

        recyclerView = findViewById(R.id.homeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapter_home(itemList, this);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        // Fetch staff category first, then load orders
        fetchStaffCategory();
    }

    private void fetchStaffCategory() {
        String staffId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("FirestoreDebug", "Fetching category for Staff ID: " + staffId);

        db.collection("users").document(staffId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    String role = document.getString("role");
                    staffCategory = document.getString("staffCategory");

                    if ("staff".equals(role) && staffCategory != null && !staffCategory.isEmpty()) {
                        Log.d("FirestoreDebug", "Staff Role: " + role + ", Category: " + staffCategory);
                        loadOrders(staffCategory);
                    } else {
                        Toast.makeText(this, "Invalid staff category or role!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "No user data found!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.e("Firestore", "Error fetching category", task.getException());
                Toast.makeText(this, "Error fetching category!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadOrders(String category) {
        CollectionReference ordersRef = db.collection("Total Orders");

        // Query only orders that match the staff's category
        Query query = ordersRef.whereEqualTo("staffCategory", category);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                itemList.clear();
                for (DocumentSnapshot document : task.getResult()) {
                    String orderId = document.getId();
                    String customerName = document.getString("customerName");
                    String status = document.getString("status");
                    String sampleName = document.getString("sampleName");

                    itemList.add(new ItemData(orderId, customerName, status, sampleName));
                }
                adapter.notifyDataSetChanged();
            } else {
                Log.e("Firestore", "Error getting orders", task.getException());
                Toast.makeText(this, "Error loading orders!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
