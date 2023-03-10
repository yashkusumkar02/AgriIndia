package com.example.agriindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddPost extends AppCompatActivity {

    TextInputLayout url, title, desc;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        String username = getIntent().getStringExtra("username");
        url = findViewById(R.id.url);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.description);
        create = findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleT = title.getEditText().getText().toString().trim();
                String descriptionT = desc.getEditText().getText().toString().trim();
                String pulT = url.getEditText().getText().toString().trim();

                if (titleT.isEmpty()) {
                    title.setError("Field Cannot Be Empty");
                } else if (descriptionT.isEmpty()) {
                    desc.setError("Filed Cannot Be Empty");
                } else if (pulT.isEmpty()) {
                    url.setError("Field Cannot Be Empty");
                } else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("Title", titleT);
                    map.put("Description", descriptionT);
                    map.put("purl", pulT);
                    map.put("Name", username);

                    FirebaseDatabase.getInstance().getReference("Post").push()
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                                    title.getEditText().setText("");
                                    desc.getEditText().setText("");
                                    url.getEditText().setText("");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Could not insert", Toast.LENGTH_LONG).show();
                                }
                            });

                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        String username = getIntent().getStringExtra("username");
        Intent intent = new Intent(AddPost.this, MainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}