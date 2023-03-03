package com.example.agriindia.LoginSignUp;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.agriindia.MainActivity;
import com.example.agriindia.R;
import com.example.agriindia.UserHelperClass;
import com.example.agriindia.model.cartModel;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterEmailActivity extends AppCompatActivity {

    TextInputLayout regfullname, regphonenumber, regemailaddress, regusername, regpassword;

    Button button;

    TextView regToLoginButton;

    FirebaseDatabase rootNode;

    Task<Void> reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);

        button=findViewById(R.id.registerButton);

        regfullname=findViewById(R.id.textInputName);
        regphonenumber=findViewById(R.id.textInputPhoneNumber);
        regemailaddress=findViewById(R.id.textInputEmail);
        regusername=findViewById(R.id.textInputUsername);
        regpassword=findViewById(R.id.textInputPassword);


        changeStatusBarColor();

    }

    public void changeStatusBarColor()
    {
        if (Build.VERSION.SDK_INT >= LOLLIPOP) {

            Window window= getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view)
    {
        startActivity(new Intent(this,LoginEmailActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private Boolean validateName() {
        String val = regfullname.getEditText().getText().toString();
        if (val.isEmpty()) {
            regfullname.setError("Field cannot be empty");
            return false;
        }
        else {
            regfullname.setError(null);
            regfullname.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regusername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            regusername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regusername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regusername.setError("White Spaces are not allowed");
            return false;
        } else {
            regusername.setError(null);
            regusername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regemailaddress.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            regemailaddress.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regemailaddress.setError("Invalid email address");
            return false;
        } else {
            regemailaddress.setError(null);
            regemailaddress.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regphonenumber.getEditText().getText().toString();
        if (val.isEmpty()) {
            regphonenumber.setError("Field cannot be empty");
            return false;
        } else {
            regphonenumber.setError(null);
            regphonenumber.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regpassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            regpassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regpassword.setError("Password is too weak");
            return false;
        } else {
            regpassword.setError(null);
            regpassword.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser (View view) {

        if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()){
            return;
        }


        //Get All the Values
        String name =regfullname.getEditText().getText().toString();
        String username = regusername.getEditText().getText().toString();
        String email = regemailaddress.getEditText().getText().toString();
        String phoneNo = regphonenumber.getEditText().getText().toString();
        String password = regpassword.getEditText().getText().toString();


        String address = "Enter your address";


        Intent intent = new Intent(getApplicationContext(),VerifyOTP.class);
        intent.putExtra("phoneNo", phoneNo);
        startActivity(intent);



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("users");

        //Stroing Data in Firebase
        //UserHelperClass helperClass = new UserHelperClass( name, username, email, phoneNo, password,address);
       // reference.child(username).setValue(helperClass);

//        Intent intent = new Intent(getApplicationContext(), LoginEmailActivity.class);
//        startActivity(intent);
//        finish();

    }




}