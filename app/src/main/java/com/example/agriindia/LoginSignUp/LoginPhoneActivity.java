package com.example.agriindia.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.agriindia.MainActivity;
import com.example.agriindia.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPhoneActivity extends AppCompatActivity {
    Button loginBtn;
    TextView regHere;
    TextInputLayout Username,Password;
    public static final String SHARED_PREFS = "sharedPrefs";

    String usernameFromDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginPhoneActivity.this,R.color.app2));


        Username = findViewById(R.id.textInputUsername);
        Password = findViewById(R.id.textInputPassword);
        loginBtn  = findViewById(R.id.loginButton);
        checkcred();

    }
    private void checkcred(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString("name","");
        String user = sharedPreferences.getString("username","");
        String pass = sharedPreferences.getString("password","");
        if (check.equals("true")){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("username",user);
            intent.putExtra("password",pass);
            intent.putExtra("name",check);
            startActivity(intent);
            finish();
        }
    }

    public boolean validationUsername(){
        String username = Username.getEditText().getText().toString().trim();
        if (username.isEmpty()){
            Username.setError("Field Cannot Be Empty");
            return false;
        }
        else {
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validationPassword(){
        String password = Password.getEditText().getText().toString().trim();

        if (password.isEmpty()){
            Password.setError("Field Cannot Be Empty");
            return false;
        }
        else {
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view){
        if(!validationUsername() | !validationPassword()){
            return;
        }
        else {
            checkUser();
        }
    }
    private void checkUser(){
        final String enteredUsername = Username.getEditText().getText().toString().trim();
        final String enteredPassword = Password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(enteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Username.setError(null);
                    Username.setErrorEnabled(false);

                    String passFromDB = snapshot.child(enteredUsername).child("password").getValue(String.class);
                    if (passFromDB.equals(enteredPassword)){

                        Username.setError(null);
                        Username.setErrorEnabled(false);



                        String nameFromDB = snapshot.child(enteredUsername).child("fname").getValue(String.class);
                        String emailFromDB = snapshot.child(enteredUsername).child("email").getValue(String.class);
                        String phoneFromDB = snapshot.child(enteredUsername).child("phoneNo").getValue(String.class);
                        usernameFromDB = snapshot.child(enteredUsername).child("username").getValue(String.class);
                        String addressFromDB = snapshot.child(enteredUsername).child("address").getValue(String.class);

                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name","true");
                        editor.putString("username",usernameFromDB);
                        editor.putString("password","true");
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phoneNo",phoneFromDB);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("username1",enteredUsername);
                        intent.putExtra("password1",enteredPassword);
                        intent.putExtra("password",passFromDB);
                        intent.putExtra("address",addressFromDB);
                        startActivity(intent);
                        finish();

                    }
                    else {
                        String passFromDB1 = snapshot.child(enteredUsername).child("password").getValue().toString();
                        Password.setError("Wrong Password");
                        Password.requestFocus();}
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

//    public void callForgotPassword(View view){
//        startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
//    }

    public void onLoginClick(View view)
    {
        startActivity(new Intent(this, RegisterPhoneActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

}