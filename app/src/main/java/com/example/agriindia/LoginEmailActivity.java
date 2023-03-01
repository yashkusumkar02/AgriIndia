package com.example.agriindia;

import static android.os.Build.VERSION_CODES.M;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import br.com.simplepass.loading_button_lib.animatedDrawables.CircularAnimatedDrawable;
import br.com.simplepass.loading_button_lib.animatedDrawables.CircularRevealAnimatedDrawable;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class LoginEmailActivity extends AppCompatActivity {


    Button button, forgetP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        button = findViewById(R.id.loginButton);

        forgetP=findViewById(R.id.textForgetPassword);

        forgetP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginEmailActivity.this,MakeSelection.class);
                startActivity(intent);
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginEmailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void onLoginClick(View view)
    {
        startActivity(new Intent(this, RegisterEmailActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }





}