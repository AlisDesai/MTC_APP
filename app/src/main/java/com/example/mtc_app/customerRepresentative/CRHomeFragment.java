package com.example.mtc_app.customerRepresentative;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mtc_app.R;

public class CRHomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CRHomeFragment() {
        // Required empty public constructor
    }

    // Factory method to create a new instance of HomeFragment
    public static CRHomeFragment newInstance(String param1, String param2) {
        CRHomeFragment fragment = new CRHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cr_home, container, false);

        // Find the customer card views by their IDs
        View customerCard1 = view.findViewById(R.id.Customer1);
        View customerCard2 = view.findViewById(R.id.Customer2);
        View customerCard3 = view.findViewById(R.id.Customer3);
        View customerCard4 = view.findViewById(R.id.Customer4);
        View customerCard5 = view.findViewById(R.id.Customer5); // Ensure the ID is correct for your customer card

        // Set OnClickListener for each customer card to navigate to CustomerDetailsFragment
        customerCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerDetailsFragment();
            }
        });

        customerCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerDetailsFragment();
            }
        });

        customerCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerDetailsFragment();
            }
        });

        customerCard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerDetailsFragment();
            }
        });

        customerCard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerDetailsFragment();
            }
        });

        return view;
    }

    // Helper method to handle the fragment transition
    private void openCustomerDetailsFragment() {
        // Create an instance of CustomerDetails fragment
        CustomerDetails customerDetailsFragment = new CustomerDetails();

        // Begin the fragment transaction
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

        // Replace the current fragment with CustomerDetails
        transaction.replace(R.id.fragment_container, customerDetailsFragment);
        transaction.addToBackStack(null); // Optionally add to the back stack for navigation
        transaction.commit();
    }
}
