package com.example.mtc_app.customer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.R;

public class AddMaterialsActivity extends AppCompatActivity {

    private EditText materialNameEditText, quantityEditText, dispatchMethodEditText;
    private Button submitButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_materials);

        materialNameEditText = findViewById(R.id.materialNameEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        dispatchMethodEditText = findViewById(R.id.dispatchMethodEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic to handle material submission (e.g., saving to Firebase)
                String materialName = materialNameEditText.getText().toString();
                String quantity = quantityEditText.getText().toString();
                String dispatchMethod = dispatchMethodEditText.getText().toString();

                // Here you can add the logic to save the data to Firebase Firestore
            }
        });
    }
}
