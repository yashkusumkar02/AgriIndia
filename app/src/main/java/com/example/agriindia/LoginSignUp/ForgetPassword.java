package com.example.agriindia.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.agriindia.R;

public class ForgetPassword extends AppCompatActivity {

    Button btn1;
    ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        btn1=findViewById(R.id.forget_password_next_btn);
        backbtn= findViewById(R.id.back_forget_password);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ForgetPassword.this, LoginEmailActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgetPassword.this,VerifyOTP.class);
                startActivity(intent);
                finish();
            }
        });
    }


}