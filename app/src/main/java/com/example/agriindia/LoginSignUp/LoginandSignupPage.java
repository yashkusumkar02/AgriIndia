package com.example.agriindia.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.agriindia.MainActivity;
import com.example.agriindia.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginandSignupPage extends AppCompatActivity {

    private MaterialButton materialButton1, materialButton2, materialButton3;

    private static final String RC_SIGN_IN= String.valueOf(100);

    private GoogleSignInClient googleSignInClient;

    private FirebaseAuth firebaseAuth;

    private static final String TAG="GOOGLE_SIGN_IN_TAG";

    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginand_signup_page);

        materialButton1=findViewById(R.id.continueviasigninbuttonnew);
        materialButton2=findViewById(R.id.continueviagooglebuttonnew);
        materialButton3=findViewById(R.id.continueviaphonebuttonnew);

        GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                                .build();

        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions );

        firebaseAuth =FirebaseAuth.getInstance();
        checkUser();

        materialButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onCLick: begin Google SignIn");
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, Integer.parseInt(RC_SIGN_IN));

            }
        });

        materialButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginandSignupPage.this, LoginEmailActivity.class);
                startActivity(intent);
                finish();
            }
        });

        materialButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginandSignupPage.this, LoginPhoneActivity.class);
                startActivity(intent);

            }
        });

    }

    private void checkUser()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser!=null){

            Log.d(TAG,"checkUser: Already Logged In");
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == RC_SIGN_IN) {
//            Log.d(TAG,"onActivityResult: Google Signin intent result");
//            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount account= accountTask.getResult(ApiException.class);
//                firebaseAuthWithGoogleAccount(account);
//
//            } catch (Exception e){
//                Log.d(TAG,"onActivityResult: "+e.getMessage());
//            }

//        }
    }

    private void firebaseAuthWithGoogleAccount(GoogleSignInAccount account) {
        Log.d(TAG,"firebaseAuthWithGoogleAccount: begin firebase auth with google account");
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        Log.d(TAG,"onSuccess: Logged In");

                        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                        String uid= firebaseUser.getUid();
                        String email= firebaseUser.getEmail();

                        Log.d(TAG,"onSuccess: Email: "+ email);
                        Log.d(TAG, "onSuccess: UID: "+ uid);

                        if (authResult.getAdditionalUserInfo().isNewUser()){
                            Log.d(TAG,"onSuccess: Account Created....!\n" +email);
                            Toast.makeText(LoginandSignupPage.this, "Account Created....!"+email, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Log.d(TAG,"onSuccess: Exisiting User...!\n"+email);
                            Toast.makeText(LoginandSignupPage.this, "Exisiting User...!\n"+email, Toast.LENGTH_SHORT).show();
                        }

                        startActivity(new Intent(LoginandSignupPage.this, MainActivity.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.d(TAG,"onFailure: Loggin Failed..!" +e.getMessage());

                    }
                });
    }
}