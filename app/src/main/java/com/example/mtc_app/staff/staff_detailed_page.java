package com.example.mtc_app.staff;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.List;

public class staff_detailed_page extends AppCompatActivity {

    private TextView customerName, contactInfo, phoneNo, sampleName, quantity, labJobNo, dispatchMode, status, testsPerformed;
    private Button updateInfoButton, deleteInfoButton;
    private FirebaseFirestore db;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_detailed_page);

        customerName = findViewById(R.id.customerName);
        contactInfo = findViewById(R.id.contactInfo);
        phoneNo = findViewById(R.id.phoneNo);
        sampleName = findViewById(R.id.sampleName);
        quantity = findViewById(R.id.quantity);
        labJobNo = findViewById(R.id.labJobNo);
        dispatchMode = findViewById(R.id.dispatchMode);
        status = findViewById(R.id.status);
        testsPerformed = findViewById(R.id.testsPerformed);

        updateInfoButton = findViewById(R.id.buttonUpdate);
        deleteInfoButton = findViewById(R.id.buttonDelete);

        db = FirebaseFirestore.getInstance();
        orderId = getIntent().getStringExtra("orderId");

        if (orderId != null) {
            loadOrderDetails(orderId);
        }

        deleteInfoButton.setOnClickListener(v -> deleteOrder(orderId));
    }

    private void loadOrderDetails(String orderId) {
        DocumentReference orderRef = db.collection("Total Orders").document(orderId);

        orderRef.get().addOnSuccessListener(document -> {
            if (document.exists()) {
                customerName.setText("Customer Name: " + document.getString("customer Name"));
                contactInfo.setText("Email: " + document.getString("email"));
                phoneNo.setText("Phone No: " + document.getString("mobile Number"));
                sampleName.setText("Sample Name: " + document.getString("sampleName"));
                quantity.setText("Quantity: " + document.getString("quantity"));
                labJobNo.setText("Lab Job No: " + document.getString("labJobNo"));
                dispatchMode.setText("Mode of Dispatch: " + document.getString("mode Of Dispatch"));
                status.setText("Status: " + document.getString("status"));

                List<String> testList = (List<String>) document.get("testsPerformed");
                testsPerformed.setText("Tests Performed: " + (testList != null ? String.join(", ", testList) : "N/A"));
            } else {
                Toast.makeText(this, "Order not found", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show());
    }

    private void deleteOrder(String orderId) {
        db.collection("Total Orders").document(orderId)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(staff_detailed_page.this, "Order Deleted", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(staff_detailed_page.this, "Error deleting order", Toast.LENGTH_SHORT).show());
    }
}
