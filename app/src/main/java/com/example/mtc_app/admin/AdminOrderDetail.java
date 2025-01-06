// OrderDetailActivity.java
package com.example.mtc_app.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.example.mtc_app.R;

public class AdminOrderDetail extends AppCompatActivity {

    TextView nameText, addressText, phoneText, emailText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_detail);

        nameText = findViewById(R.id.nameText);
        addressText = findViewById(R.id.addressText);
        phoneText = findViewById(R.id.phoneText);
        emailText = findViewById(R.id.emailText);

        // Get data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nameText.setText(extras.getString("name"));
            addressText.setText(extras.getString("address"));
            phoneText.setText(extras.getString("phone"));
            emailText.setText(extras.getString("email"));
        }
    }
}
