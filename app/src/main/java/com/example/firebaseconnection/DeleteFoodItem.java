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
public class DeleteFoodItem extends AppCompatActivity {

    private EditText deleteByIdEditText;
    private EditText deleteByNameEditText;
    private Button deleteButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_food_item);

        deleteByIdEditText = findViewById(R.id.deleteFoodByID);
        deleteByNameEditText = findViewById(R.id.deleteFoodByName);
        deleteButton = findViewById(R.id.deleteBtn);

        // Get a reference to the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("food");

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFoodItem();
            }
        });
    }

    public void deleteFoodItem() {
        String foodItemId = deleteByIdEditText.getText().toString().trim();

        // Delete the food from the Firebase Realtime Database
        databaseReference.child(foodItemId).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        deleteByIdEditText.setText("");
                        deleteByNameEditText.setText("");
                        Toast.makeText(DeleteFoodItem.this, "Food item deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        deleteByIdEditText.setText("");
                        deleteByNameEditText.setText("");
                        Toast.makeText(DeleteFoodItem.this, "Failed to delete food item: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}