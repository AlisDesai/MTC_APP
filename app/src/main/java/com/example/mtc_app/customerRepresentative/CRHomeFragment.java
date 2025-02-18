package com.example.mtc_app.customerRepresentative;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.EditText;

import com.example.mtc_app.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class CRHomeFragment extends Fragment {

    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private CustomerAdapter adapter;
    private List<Customer> customerList, filteredList;
    private EditText searchInput;

    public CRHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cr_home, container, false);

        db = FirebaseFirestore.getInstance();
        recyclerView = view.findViewById(R.id.recyclerViewCustomers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchInput = view.findViewById(R.id.searchInput);

        customerList = new ArrayList<>();
        filteredList = new ArrayList<>();
        adapter = new CustomerAdapter(filteredList, this::openCustomerDetailsFragment);
        recyclerView.setAdapter(adapter);

        loadCustomerData();
        setupSearchFunctionality();

        return view;
    }

    private void loadCustomerData() {
        CollectionReference usersRef = db.collection("users");

        usersRef.whereEqualTo("role", "customer")
                .orderBy("created_at", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    customerList.clear();
                    filteredList.clear();

                    for (DocumentSnapshot document : queryDocumentSnapshots) {
                        Customer customer = document.toObject(Customer.class);
                        if (customer != null) {
                            customer.setId(document.getId()); // Set Firestore document ID manually
                            customerList.add(customer);
                        }
                    }

                    filteredList.addAll(customerList);
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error fetching customer data", e));
    }


    private void setupSearchFunctionality() {
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCustomers(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterCustomers(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(customerList);
        } else {
            for (Customer customer : customerList) {
                if (customer.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(customer);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void openCustomerDetailsFragment(Customer customer) {
        CustomerDetails customerDetailsFragment = new CustomerDetails();
        Bundle bundle = new Bundle();
        bundle.putString("customer_phone", customer.getPhone());  // Pass customer phone instead of ID
        customerDetailsFragment.setArguments(bundle);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, customerDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
