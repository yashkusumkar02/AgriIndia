package com.example.agriindia.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.agriindia.R;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPhoneActivity extends AppCompatActivity {

    TextInputLayout Fname,Username,Email,Password,Phone;
    Button signup;
    Button button;

    TextView regToLoginButton;

    FirebaseDatabase rootNode;

    Task<Void> reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);

        button=findViewById(R.id.registerButton);

        Fname=findViewById(R.id.textInputName);
        Phone=findViewById(R.id.textInputPhoneNumber);
        Email=findViewById(R.id.textInputEmail);
        Username=findViewById(R.id.textInputUsername);
        Password=findViewById(R.id.textInputPassword);

    }

    public boolean validationFname(){
        String fname = Fname.getEditText().getText().toString().trim();

        if (fname.isEmpty()){
            Fname.setError("Field Cannot Be Empty");
            Fname.setErrorEnabled(true);
            return false;
        }
        else {
            Fname.setError(null);
            Fname.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validationUsername(){
        String username = Username.getEditText().getText().toString().trim();
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
            Username.setError("Invalid Username");
            Username.setErrorEnabled(true);
            return false;
        }
        else {
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validationEmail(){
        String email = Email.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.isEmpty()){
            Email.setError("Field Cannot Be Empty");
            return false;
        }
        else if(!email.matches(emailPattern)){
            Email.setError("Invalid Email Address");
            return false;
        }
        else {
            Email.setError(null);
            Email.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validationPhoneNo(){
        String phoneNo = Phone.getEditText().getText().toString().trim();

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

    public boolean validationPassword(){
        String password = Password.getEditText().getText().toString().trim();
        String passwordValidation =   "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$";
        if (password.isEmpty()){
            Password.setError("Field Cannot Be Empty");
            Password.setErrorEnabled(true);
            return false;
        }
        else if (!password.matches(passwordValidation)){
            Password.setError("Password Is Too Weak");
            Password.setErrorEnabled(true);
            return false;
        }
        else {
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }


    public void registerUser(View view){

        if(!validationFname() | !validationUsername() | !validationEmail() | !validationPhoneNo() | !validationPassword()  ){
            return;
        }

        String fname = Fname.getEditText().getText().toString();
        String username = Username.getEditText().getText().toString();
        String email = Email.getEditText().getText().toString();
        String phoneNo = Phone.getEditText().getText().toString();
        String password = Password.getEditText().getText().toString();
        String address = "Enter Address";

        Intent intent = new Intent(RegisterPhoneActivity.this,verificationPhone.class);
        intent.putExtra("phone",phoneNo);
        intent.putExtra("name",fname);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("phoneN",phoneNo);
        intent.putExtra("password",password);
        intent.putExtra("address",address);
        startActivity(intent);


    }
}