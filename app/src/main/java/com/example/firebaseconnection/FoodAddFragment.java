package com.example.firebaseconnection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FoodAddFragment extends Fragment {
    View view;
    TextView foodId, foodName, foodCategory, foodPrice;
    Button addFoodButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_food_add, container, false);
        addFoodButton = view.findViewById(R.id.addFoodButton);

        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodId = getView().findViewById(R.id.foodID);
                foodName = getView().findViewById(R.id.foodName);
                foodCategory = getView().findViewById(R.id.foodCategory);
                foodPrice = getView().findViewById(R.id.foodPrice);

                String id = foodId.getText().toString().trim();
                String name = foodName.getText().toString().trim();
                String category = foodCategory.getText().toString().trim();
                String price = foodPrice.getText().toString().trim();

                foodCollection foodCollect = new foodCollection(name, category, price);

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference root = db.getReference("food");
                root.child(id).setValue(foodCollect);

                foodId.setText("");
                foodName.setText("");
                foodCategory.setText("");
                foodPrice.setText("");

                Toast.makeText(getContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}