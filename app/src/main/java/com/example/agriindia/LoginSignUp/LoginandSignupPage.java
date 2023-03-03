package com.example.agriindia.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.agriindia.MainActivity;
import com.example.agriindia.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginandSignupPage extends AppCompatActivity {

    private MaterialButton materialButton1, materialButton2, materialButton3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginand_signup_page);

        materialButton1 = findViewById(R.id.continueviasigninbuttonnew);
        materialButton2 = findViewById(R.id.continueviagooglebuttonnew);
        materialButton3 = findViewById(R.id.continueviaphonebuttonnew);



        materialButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginandSignupPage.this, LoginEmailActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

