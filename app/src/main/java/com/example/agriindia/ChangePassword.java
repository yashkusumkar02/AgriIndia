package com.example.agriindia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {

    TextInputLayout password;
    Button reset;
    String username1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(ChangePassword.this,R.color.white));

        username1 = getIntent().getStringExtra("username");

        password = findViewById(R.id.password);
        reset = findViewById(R.id.reset_Pass);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordChanged()){
                    Intent intent = new Intent(getApplicationContext(),PasswordChangeSuccessfully.class);
                    // Toast.makeText(getApplicationContext(), "Data has been updated", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Data is same cannot be updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isPasswordChanged() {
        if (!validationPassword()){
            return false;
        }
        else {
            final String enteredPassword = password.getEditText().getText().toString().trim();
            String username12 = getIntent().getStringExtra("username");
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference reference = firebaseDatabase.getReference("users").child(username12);
            reference.child("password").setValue(enteredPassword);
            return true;
        }
    }


    public boolean validationPassword(){
        String Password = password.getEditText().getText().toString();
        String passwordValidation =   "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$";
        if (Password.isEmpty()){
            password.setError("Field Cannot Be Empty");
            password.setErrorEnabled(true);
            return false;
        }
        else if (!Password.matches(passwordValidation)){
            password.setError("Password Is Too Weak");
            password.setErrorEnabled(true);
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}