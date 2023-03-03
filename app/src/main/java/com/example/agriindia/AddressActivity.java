package com.example.agriindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agriindia.model.cartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddressActivity extends AppCompatActivity {

    EditText address;
    Button btn;
    String purl,price,quantity,total,date,orderID,username,payment,title,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(AddressActivity.this,R.color.white));
        address = findViewById(R.id.address);
        btn = findViewById(R.id.place_order);

        purl = getIntent().getStringExtra("purl");
        price = getIntent().getStringExtra("price");
        quantity = getIntent().getStringExtra("quantity");
        total = getIntent().getStringExtra("total");
        date = getIntent().getStringExtra("date");
        orderID = getIntent().getStringExtra("orderID");
        username = getIntent().getStringExtra("username");
        payment = getIntent().getStringExtra("payment");
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");


        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        String address1 = String.valueOf(dataSnapshot.child("address").getValue());
                        address.setText(address1);
                    }
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String add = address.getText().toString();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference("Order");
                cartModel helper = new cartModel(purl,price,title,desc,quantity,total,date,orderID,payment,add);
                reference.child(username).child(title).setValue(helper);

                DatabaseReference ref = firebaseDatabase.getReference("users");
                ref.child(username).child("address").setValue(add);

                DatabaseReference referenceCart = firebaseDatabase.getReference("Cart");
                referenceCart.child(username).child(title).removeValue();

                Intent intent = new Intent(AddressActivity.this,OrderSuccessfull.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();


            }
        });

    }
}