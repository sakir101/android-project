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

public class UpdateFoodItem extends AppCompatActivity {

    private EditText foodItemIdEditText;
    private EditText foodItemNameEditText;
    private EditText foodCategoryEditText;
    private EditText foodPriceEditText;
    private Button updateButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food_item);
        foodItemIdEditText = findViewById(R.id.foodItemIDDatabase);
        foodItemNameEditText = findViewById(R.id.foodItemNameId);
        foodCategoryEditText = findViewById(R.id.foodCategoryId);
        foodPriceEditText = findViewById(R.id.foodPriceId);
        updateButton = findViewById(R.id.updateBtn);

        // Get a reference to the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("food");

      updateButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              updateFoodItem();
          }
      });
    }

    public void updateFoodItem() {
        String foodItemId = foodItemIdEditText.getText().toString().trim();
        String foodItemName = foodItemNameEditText.getText().toString().trim();
        String foodCategory = foodCategoryEditText.getText().toString().trim();
        String foodPrice = foodPriceEditText.getText().toString().trim();

        // Create a HashMap to hold the updated data
        Map<String, Object> foodItemUpdates = new HashMap<>();
        foodItemUpdates.put("name", foodItemName);
        foodItemUpdates.put("category", foodCategory);
        foodItemUpdates.put("price", foodPrice);

        // Update the employee data in the Firebase Realtime Database
        databaseReference.child(foodItemId).updateChildren(foodItemUpdates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        foodItemIdEditText.setText("");
                        foodCategoryEditText.setText("");
                        foodPriceEditText.setText("");
                        Toast.makeText(UpdateFoodItem.this, "Food Item updated successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        foodItemIdEditText.setText("");
                        foodCategoryEditText.setText("");
                        foodPriceEditText.setText("");
                        Toast.makeText(UpdateFoodItem.this, "Failed to update food item: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}