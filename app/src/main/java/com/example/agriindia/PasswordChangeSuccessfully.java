package com.example.agriindia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agriindia.LoginSignUp.LoginPhoneActivity;

public class PasswordChangeSuccessfully extends AppCompatActivity {

    private static int TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change_successfully);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(PasswordChangeSuccessfully.this, LoginPhoneActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}