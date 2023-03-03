package com.example.agriindia.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.agriindia.R;

public class VerifyOTP extends AppCompatActivity {

    Button btn1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        btn1=findViewById(R.id.verifyOTP);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VerifyOTP.this,SetNewPassword.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void callNextScreenFromOTP(View view) {
    }

    public void goToHomeFromOTP(View view) {
    }
}