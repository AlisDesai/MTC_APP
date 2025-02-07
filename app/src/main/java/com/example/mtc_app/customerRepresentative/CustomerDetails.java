package com.example.mtc_app.customerRepresentative;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mtc_app.R;
import com.google.android.material.button.MaterialButton;

public class CustomerDetails extends Fragment {

    public CustomerDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Handle "Order Details" button click
        if (orderDetailsButton != null) {
            orderDetailsButton.setOnClickListener(v -> {
                Fragment orderDetailsFragment = new OrderDetails();
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, orderDetailsFragment) // Replace with the correct container ID
                        .addToBackStack(null) // Add to backstack for navigation
                        .commit();
            });
        }

        // Handle "Edit" button click
        MaterialButton editButton = view.findViewById(R.id.editButton);
        if (editButton != null) {
            editButton.setOnClickListener(v -> {
                Fragment editCustomerFragment = new EditCustomer();
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, editCustomerFragment) // Replace with the correct container ID
                        .addToBackStack(null) // Add to backstack for navigation
                        .commit();
            });
        }

    }
}
