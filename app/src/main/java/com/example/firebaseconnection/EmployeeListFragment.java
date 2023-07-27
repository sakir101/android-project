package com.example.firebaseconnection;

import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.database.ValueEventListener;
import androidx.fragment.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;

public class EmployeeListFragment extends Fragment {

    View view;
    private ListView listViewEmployees;
    private ArrayAdapter<String> adapter;
    private List<String> employeeList;
    private DatabaseReference databaseReference;

    private Spinner mySpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_employee_list, container, false);
        mySpinner = view.findViewById(R.id.mySpinnerID);
        ArrayAdapter<CharSequence> myAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerArray, android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Intent myIntent = new Intent(requireActivity(), UpdateEmployee.class);
                    startActivity(myIntent);
                } else if (position == 2) {
                    Intent myIntent = new Intent(requireActivity(), DeleteEmployee.class);
                    startActivity(myIntent);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected in spinner
            }
        });
        listViewEmployees = view.findViewById(R.id.listViewEmployees);

        // Initialize the employee list and adapter
        employeeList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, employeeList);
        listViewEmployees.setAdapter(adapter);

        // Get a reference to the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("employee");

        // Read data from the database
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String name = childSnapshot.child("name").getValue(String.class);
                    String age = childSnapshot.child("age").getValue(String.class);
                    String duration = childSnapshot.child("duration").getValue(String.class);

                    String employeeDetails = "Name: " + name + "\nAge: " + age + "\nDuration: " + duration;

                    employeeList.add(employeeDetails);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });
        return view;

    }
}