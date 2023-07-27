package com.example.firebaseconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText passwordBox, emailBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        passwordBox = findViewById(R.id.passwordBox);
        emailBox = findViewById(R.id.emailBox);
    }

    public void checkAuthentication(View view) {
        String password = passwordBox.getText().toString();
        String email = emailBox.getText().toString();

        if (password.equals("12345") && email.equals("abc@gmail.com")){
            passwordBox.setText("");
            emailBox.setText("");
            Intent myIntent= new Intent(Login.this,HomePage.class);
            startActivity(myIntent);
        }
        else{
            passwordBox.setText("");
            emailBox.setText("");
            Toast.makeText(this, "Please provide appropriate password or email", Toast.LENGTH_SHORT).show();
        }
    }
}