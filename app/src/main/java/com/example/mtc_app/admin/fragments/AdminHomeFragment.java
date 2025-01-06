package com.example.mtc_app.admin.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mtc_app.R;
import com.example.mtc_app.admin.AdminOrderDetail;

public class AdminHomeFragment extends Fragment {

    private CardView customer1, customer2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_home, container, false);

        // Initialize CardViews
        customer1 = rootView.findViewById(R.id.Customer1);
        customer2 = rootView.findViewById(R.id.Customer2);

        // Set click listeners for CardViews
        customer1.setOnClickListener(view -> openOrderDetail("Order 1", "John Doe", "123 Main St", "9876543210", "john@example.com"));
        customer2.setOnClickListener(view -> openOrderDetail("Order 2", "Jane Smith", "456 Market St", "9876543211", "jane@example.com"));

        return rootView;
    }
    private void openOrderDetail(String order, String name, String address, String phone, String email) {
        Intent intent = new Intent(getActivity(), AdminOrderDetail.class);
        intent.putExtra("order", order);
        intent.putExtra("name", name);
        intent.putExtra("address", address);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}