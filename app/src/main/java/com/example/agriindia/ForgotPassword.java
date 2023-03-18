package com.example.agriindia;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ForgotPassword extends AppCompatActivity {

    TextInputLayout Phone,Username;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(ForgotPassword.this,R.color.white));
        setContentView(R.layout.activity_forgot_password);

        Phone = findViewById(R.id.phone);
        Username = findViewById(R.id.user);
        reset = findViewById(R.id.reset_Pass);


    }

    public boolean validationPhoneNo(){
        String phoneNo = Phone.getEditText().getText().toString();

        if (phoneNo.isEmpty()){
            Phone.setError("Field Cannot Be Empty");
            Phone.setErrorEnabled(true);
            return false;
        }
        else {
            Phone.setError(null);
            Phone.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validationUsername(){
        String username = Username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (username.isEmpty()){
            Username.setError("Field Cannot Be Empty");
            Username.setErrorEnabled(true);
            return false;
        }
        else if(username.length()>=15){
            Username.setError("Username Too Long");
            Username.setErrorEnabled(true);
            return false;
        }
        else if(!username.matches(noWhiteSpace)){
            Username.setError("Username Too Long");
            Username.setErrorEnabled(true);
            return false;
        }
        else {
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }
    }

    public void forgotPassword(View view) {
        if(!validationUsername() | !validationPhoneNo()){
            return;
        }
        else {
            checkUser();
        }
    }

    private void checkUser() {
        final String enteredUsername = Username.getEditText().getText().toString().trim();
        final String enteredPassword = Phone.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(enteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Username.setError(null);
                    Username.setErrorEnabled(false);

                    String passFromDB = snapshot.child(enteredUsername).child("phoneNo").getValue(String.class);
                    if (passFromDB.equals(enteredPassword)){

                        Username.setError(null);
                        Username.setErrorEnabled(false);

                        Intent intent = new Intent(getApplicationContext(),forgotPasswordVerificationCode.class);
                        intent.putExtra("phone",enteredPassword);
                        intent.putExtra("username",enteredUsername);
                        startActivity(intent);
                        finish();
//                        FirebaseDatabase.getInstance().getReference("users")
//                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                .addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                                        GlobalVar.currentUser = snapshot.getValue(UserHelperClass.class);
//
//                                    }
//                                    @Override
//                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//                                    }
//                                });
                    }
                    else {
                        String passFromDB1 = snapshot.child(enteredUsername).child("phoneNo").getValue().toString();
                        Phone.setError("Enter Valid Number");
                        Phone.requestFocus();}
                }
                else {
                    Username.setError("User Not exists");
                    Username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

