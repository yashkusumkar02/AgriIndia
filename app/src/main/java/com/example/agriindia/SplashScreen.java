package com.example.agriindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.agriindia.LoginSignUp.LoginandSignupPage;

public class SplashScreen extends AppCompatActivity {

    Handler handler;

    private static int SPLASH_TIME_OUT=2000;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences("SharedPref", MODE_PRIVATE);
                boolean isFirstTime = sharedPreferences.getBoolean("First Time", true);

                if (isFirstTime) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("FirstTime", false);
                    editor.apply();

                    startActivity(new Intent(SplashScreen.this,LoginandSignupPage.class));
                }
                else {
                    Intent intent=new Intent(SplashScreen.this,LoginandSignupPage.class);
                    startActivity(intent);
                    finish();
                }

            }
        },SPLASH_TIME_OUT);
    }
}