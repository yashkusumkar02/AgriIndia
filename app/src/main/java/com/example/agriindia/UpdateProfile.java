package com.example.agriindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.agriindia.Fragments.OtherFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateProfile extends AppCompatActivity {

    ImageButton imageButton;

    Button update;
    TextInputLayout name,mob,email,password,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        imageButton=findViewById(R.id.closeIcon);


        name = findViewById(R.id.fullname);
        mob = findViewById(R.id.phoneNo);
        email = findViewById(R.id.emailAdd);
        password = findViewById(R.id.password);
        update = findViewById(R.id.updateButton);
        address = findViewById(R.id.address);


        final String username = getIntent().getStringExtra("username");
        final String n = getIntent().getStringExtra("name");
        final String m = getIntent().getStringExtra("phone");
        final String e = getIntent().getStringExtra("email");
        final String p = getIntent().getStringExtra("password");
        final String a = getIntent().getStringExtra("address");


        name.getEditText().setText(n);
        mob.getEditText().setText(m);
        password.getEditText().setText(p);
        email.getEditText().setText(e);
        address.getEditText().setText(a);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameT = name.getEditText().getText().toString().trim();
                String mobT = mob.getEditText().getText().toString().trim();
                String emailT = email.getEditText().getText().toString().trim();
                String passwordT = password.getEditText().getText().toString().trim();
                String addressT = address.getEditText().getText().toString().trim();

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference("users");
                UserHelperClass helperClass = new UserHelperClass(nameT,username,emailT,mobT,passwordT,addressT);
                reference.child(username).setValue(helperClass);

                Toast.makeText(getApplication(),"Data Has Been Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onBackPressed(){
//        final String username = getIntent().getStringExtra("username");
//        Intent intent = new Intent(UpdateProfile.this,HomeActivity.class);
//        intent.putExtra("username",username);
//        startActivity(intent);
        finish();
//        super.onBackPressed();
    }
}