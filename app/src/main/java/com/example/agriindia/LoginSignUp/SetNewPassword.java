package com.example.agriindia.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.agriindia.R;

public class SetNewPassword extends AppCompatActivity {

    ImageButton backbtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        backbtn1=findViewById(R.id.back_new_password);
        backbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SetNewPassword.this,LoginEmailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}