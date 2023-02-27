package com.example.agriindia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginEmailActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    ImageButton imageButton;
    MaterialButton mate1, mate2;
    EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        imageButton=findViewById(R.id.toolbar_backbtn);
        mate1=findViewById(R.id.loginbtn);
        mate2=findViewById(R.id.signup_btn);
        editText1=findViewById(R.id.emailEt);
        editText2=findViewById(R.id.passwordPt);

        firebaseAuth =FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginEmailActivity.this, RegisterEmailActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

    }
    private  String email, password;

    private void validateData() {
        email= editText1.getText().toString().trim();
        password= editText2.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText1.setError("Invalid Email...");
            editText1.requestFocus();
        } else if (password.isEmpty()) {
            editText2.setError("Enter Password");
            editText2.requestFocus();
        }
        else {
            loginUser();
        }

    }

    private void loginUser() {
        progressDialog.setMessage("Logging In...!");
        progressDialog.show();

        firebaseAuth
    }
}