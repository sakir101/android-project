package com.example.firebaseconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
    }

    public void logInPage(View view) {
        Intent myIntent= new Intent(StartPage.this,Login.class);
        startActivity(myIntent);
    }
}