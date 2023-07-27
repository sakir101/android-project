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
public class DeleteEmployee extends AppCompatActivity {
    private EditText deleteByIdEditText;
    private EditText deleteByNameEditText;
    private Button deleteButton;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_employee);

        deleteByIdEditText = findViewById(R.id.deleteByID);
        deleteByNameEditText = findViewById(R.id.deleteByName);
        deleteButton = findViewById(R.id.deleteBtn);

        // Get a reference to the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("employee");

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });

    }

    public void deleteEmployee() {
        String employeeId = deleteByIdEditText.getText().toString().trim();

        // Delete the employee from the Firebase Realtime Database
        databaseReference.child(employeeId).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        deleteByIdEditText.setText("");
                        deleteByNameEditText.setText("");
                        Toast.makeText(DeleteEmployee.this, "Employee deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        deleteByIdEditText.setText("");
                        deleteByNameEditText.setText("");
                        Toast.makeText(DeleteEmployee.this, "Failed to delete employee: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}