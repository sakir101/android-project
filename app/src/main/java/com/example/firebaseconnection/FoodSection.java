package com.example.firebaseconnection;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FoodSection extends AppCompatActivity {
     Button addFood, foodList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_section);

        addFood = findViewById(R.id.addFood);
        foodList = findViewById(R.id.foodList);

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(new FoodAddFragment());
            }
        });

        foodList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(new FoodListFragment());
            }
        });
    }

    private void replace(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutFoodID,fragment);
        fragmentTransaction.commit();
    }
}