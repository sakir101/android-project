package com.example.firebaseconnection;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {



    Button employeeBtn, employeeBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeBtn = findViewById(R.id.addEmployee);
        employeeBtn2 = findViewById(R.id.employeeList);

        employeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(new EmployeeFragment());
            }
        });

        employeeBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(new EmployeeListFragment());
            }
        });
    }

    private void replace(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutID,fragment);
        fragmentTransaction.commit();
    }


}