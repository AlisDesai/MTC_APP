package com.example.mtc_app.customer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtc_app.R;
import com.example.mtc_app.customer.CustomerHomePageActivity;
import com.example.mtc_app.customer.adapter.ItemData;
import com.example.mtc_app.customer.adapter.adapter_home;
import com.example.mtc_app.customer.orders.CustomerOrderDetails;
import com.example.mtc_app.helpAndSupport.HelpAndSupportActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView homeRecyclerView;
    private adapter_home homeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);

        // Initialize the RecyclerView
        homeRecyclerView = view.findViewById(R.id.homeRecyclerView);

        // Set up the RecyclerView layout manager
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the adapter with dummy data
        List<ItemData> dataItems = getDummyData();
        homeAdapter = new adapter_home(dataItems, getContext());

        // Attach the adapter to the RecyclerView
        homeRecyclerView.setAdapter(homeAdapter);

        // Handle item click events in RecyclerView
        homeAdapter.setOnItemClickListener(position -> {
            // Redirect to the Detailed Page with selected item data
            ItemData selectedItem = dataItems.get(position);
            Intent intent = new Intent(requireContext(), CustomerOrderDetails.class);
            intent.putExtra("itemName", selectedItem.getTitle());
            startActivity(intent);
        });

        return view;
    }

    private List<ItemData> getDummyData() {
        List<ItemData> data = new ArrayList<>();
        data.add(new ItemData("Title 1", "Subtitle 1", R.drawable.ic_users));
        data.add(new ItemData("Title 2", "Subtitle 2", R.drawable.ic_profile));
        data.add(new ItemData("Title 3", "Subtitle 3", R.drawable.ic_arrow_back));
        return data;
    }
}
