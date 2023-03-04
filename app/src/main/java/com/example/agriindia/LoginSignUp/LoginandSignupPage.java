package com.example.agriindia.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.agriindia.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginandSignupPage extends AppCompatActivity {

    private MaterialButton materialButton1, materialButton2, materialButton3;

    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginand_signup_page);

        materialButton1=findViewById(R.id.continueviasigninbuttonnew);
        materialButton2=findViewById(R.id.continueviagooglebuttonnew);
        materialButton3=findViewById(R.id.continueviaphonebuttonnew);

        materialButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginandSignupPage.this, LoginEmailActivity.class);
                startActivity(intent);
                finish();
            }
        });

        materialButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginandSignupPage.this, LoginPhoneActivity.class);
                startActivity(intent);

            }
        });

    }
}