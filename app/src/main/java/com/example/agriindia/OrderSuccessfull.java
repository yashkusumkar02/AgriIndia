package com.example.agriindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class OrderSuccessfull extends AppCompatActivity {
    private static int TIME_OUT = 2000;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_successfull);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        username = getIntent().getStringExtra("username");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(OrderSuccessfull.this,MainActivity.class);
                i.putExtra("username",username);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}