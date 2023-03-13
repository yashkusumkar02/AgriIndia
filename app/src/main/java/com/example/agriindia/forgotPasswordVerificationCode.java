package com.example.agriindia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class forgotPasswordVerificationCode extends AppCompatActivity {


    PinView pin ;
    Button next;
    String phoneNumber,otpID;
    FirebaseAuth firebaseAuth;
    String WhatToDo,phoneNo,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_phone);


        pin = findViewById(R.id.pinView);
        next = findViewById(R.id.verificationBtn);
        firebaseAuth = FirebaseAuth.getInstance();
        phoneNumber = getIntent().getStringExtra("phone").toString();
        username = getIntent().getStringExtra("username");
        initiateOTP();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pin.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Field Not Empty",Toast.LENGTH_SHORT).show();
                else if (pin.getText().toString().length() != 6)
                    Toast.makeText(getApplicationContext(),"Wrong OTP",Toast.LENGTH_SHORT).show();
                else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpID,pin.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

    }

    private void initiateOTP() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpID = s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(forgotPasswordVerificationCode.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(forgotPasswordVerificationCode.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String username = getIntent().getStringExtra("username");
                            Intent intent = new Intent(getApplicationContext(),ChangePassword.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("username",username);
                            startActivity(intent);
                            finish();

                        }
                        else {
                            Toast.makeText(forgotPasswordVerificationCode.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}