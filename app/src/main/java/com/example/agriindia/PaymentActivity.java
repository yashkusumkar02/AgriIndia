package com.example.agriindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentActivity extends AppCompatActivity {
    RadioButton radioButton;
    Button paymentBtn;
    RadioGroup radioGroup;
    String purl,price,quantity,total,date,orderID,username,title,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(PaymentActivity.this,R.color.white));
        purl = getIntent().getStringExtra("purl");
        price = getIntent().getStringExtra("price");
        quantity = getIntent().getStringExtra("quantity");
        total = getIntent().getStringExtra("total");
        date = getIntent().getStringExtra("date");
        orderID = getIntent().getStringExtra("orderID");
        username = getIntent().getStringExtra("username");
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");

        paymentBtn = findViewById(R.id.place_order);
        radioGroup = findViewById(R.id.payment);

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=radioGroup.getCheckedRadioButtonId();
                radioButton=(RadioButton)findViewById(selectedId);
//                Toast.makeText(PlaceOrder.this,"Order Placed",Toast.LENGTH_SHORT).show();
//                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//                DatabaseReference reference = firebaseDatabase.getReference("Order").child(username1);
//                modelOrder helperClass = new modelOrder(name1,username1,email1,phone1,address1,itemName,itemDet,itemPrice,photo,size,style,color,gendor);
//                reference.child(itemName).setValue(helperClass);
//                FirebaseDatabase.getInstance().getReference("OrderCart").child(username1)
//                        .child(itemName).removeValue();
//                DatabaseReference reference1 = firebaseDatabase.getReference().child("AdminOrder");
//                reference1.child(itemName).setValue(helperClass);

                String payment = "Cash On Delivery";
                Intent intent = new Intent(PaymentActivity.this,AddressActivity.class);
                intent.putExtra("purl",purl);
                intent.putExtra("price",price);
                intent.putExtra("quantity",quantity);
                intent.putExtra("total",total);
                intent.putExtra("date",date);
                intent.putExtra("orderID",orderID);
                intent.putExtra("username",username);
                intent.putExtra("payment",payment);
                intent.putExtra("title",title);
                intent.putExtra("desc",desc);
                startActivity(intent);
                finish();
            }
        });

    }
}