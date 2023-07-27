package com.example.firebaseconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void employeePage(View view) {
        Intent myIntent= new Intent(HomePage.this,MainActivity.class);
        startActivity(myIntent);
    }

    public void foodPage(View view) {
        Intent myIntent= new Intent(HomePage.this,FoodSection.class);
        startActivity(myIntent);
    }
}