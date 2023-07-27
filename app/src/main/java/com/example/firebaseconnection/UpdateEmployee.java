package com.example.firebaseconnection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateEmployee extends AppCompatActivity {
    private EditText employeeIdEditText;
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText durationEditText;
    private Button updateButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);
        employeeIdEditText = findViewById(R.id.employeeIDDatabase);
        nameEditText = findViewById(R.id.nameId);
        ageEditText = findViewById(R.id.ageId);
        durationEditText = findViewById(R.id.durationId);
        updateButton = findViewById(R.id.updateBtn);

        // Get a reference to the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("employee");

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });
    }

    public void updateEmployee() {
        String employeeId = employeeIdEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();
        String duration = durationEditText.getText().toString().trim();

        // Create a HashMap to hold the updated data
        Map<String, Object> employeeUpdates = new HashMap<>();
        employeeUpdates.put("name", name);
        employeeUpdates.put("age", age);
        employeeUpdates.put("duration", duration);

        // Update the employee data in the Firebase Realtime Database
        databaseReference.child(employeeId).updateChildren(employeeUpdates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        nameEditText.setText("");
                        ageEditText.setText("");
                        durationEditText.setText("");
                        Toast.makeText(UpdateEmployee.this, "Employee updated successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        nameEditText.setText("");
                        ageEditText.setText("");
                        durationEditText.setText("");
                        Toast.makeText(UpdateEmployee.this, "Failed to update employee: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}