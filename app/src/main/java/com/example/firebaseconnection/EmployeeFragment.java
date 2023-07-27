package com.example.firebaseconnection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeeFragment extends Fragment {
    TextView text1, text2, text3, text4;
    Button sendButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee, container, false);
        sendButton = view.findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1 = getView().findViewById(R.id.text1);
                text2 = getView().findViewById(R.id.text2);
                text3 = getView().findViewById(R.id.text3);
                text4 = getView().findViewById(R.id.text4);

                String id = text1.getText().toString().trim();
                String name = text2.getText().toString().trim();
                String age = text3.getText().toString().trim();
                String duration = text4.getText().toString().trim();

                collectData collection = new collectData(name, age, duration);

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference root = db.getReference("employee");
                root.child(id).setValue(collection);

                text1.setText("");
                text2.setText("");
                text3.setText("");
                text4.setText("");

                Toast.makeText(getContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}