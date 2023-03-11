package com.example.agriindia.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.agriindia.LoginSignUp.LoginandSignupPage;
import com.example.agriindia.R;
import com.example.agriindia.UpdateProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OtherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OtherFragment extends Fragment {

    TextInputLayout fullname, userName, emailadd, password, phoneNo;

    private FirebaseAuth firebaseAuth;

    private Button logoutbtn;

    private ImageButton imageButton;

    TextView username, fullName;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OtherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OtherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OtherFragment newInstance(String param1, String param2) {
        OtherFragment fragment = new OtherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_other, container, false);

        fullname = view.findViewById(R.id.fullname);
        fullName = view.findViewById(R.id.fullnameUser);
        username = view.findViewById(R.id.usernameFull);
        emailadd = view.findViewById(R.id.emailAdd);
        password = view.findViewById(R.id.password);
        phoneNo = view.findViewById(R.id.phoneNo);
        logoutbtn = (Button) view.findViewById(R.id.logoutButton);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(logoutbtn.getContext());
                dialog.setTitle("LOGOUT");
                dialog.setMessage("Are You Sure You Want To Logout ?");


                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth.getInstance().signOut();
                        SharedPreferences preferences = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent(getActivity(),LoginandSignupPage.class);
                        startActivity(intent);
                        getActivity().finish();

                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
//        checkUser();

        imageButton = view.findViewById(R.id.editForm);

        showAllUserData();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentI = getActivity().getIntent();
                String user_username = intentI.getStringExtra("username");
                DatabaseReference reference;
                reference = FirebaseDatabase.getInstance().getReference("users");
                reference.child(user_username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                DataSnapshot dataSnapshot = task.getResult();
                                String name1 = String.valueOf(dataSnapshot.child("name").getValue());
                                String email1 = String.valueOf(dataSnapshot.child("email").getValue());
                                String phoneNo1 = String.valueOf(dataSnapshot.child("phoneNo").getValue());
                                String password1 = String.valueOf(dataSnapshot.child("password").getValue());
                                String username1 = String.valueOf(dataSnapshot.child("username").getValue());
                                String address1 = String.valueOf(dataSnapshot.child("address").getValue());


                                Intent intent = new Intent(getActivity(), UpdateProfile.class);
                                intent.putExtra("username", user_username);
                                intent.putExtra("name", name1);
                                intent.putExtra("email", email1);
                                intent.putExtra("phone", phoneNo1);
                                intent.putExtra("password", password1);
                                intent.putExtra("address", address1);
                                startActivity(intent);
                            }
                        }
                    }
                });
            }
        });
        return view;
    }




    private void showAllUserData() {

        Intent intent = getActivity().getIntent();
        String usernameFromDB = intent.getStringExtra("username");


        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.child(usernameFromDB).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String name1 = String.valueOf(dataSnapshot.child("name").getValue());
                        String email1 = String.valueOf(dataSnapshot.child("email").getValue());
                        String phoneNo1 = String.valueOf(dataSnapshot.child("phoneNo").getValue());
                        String password1 = String.valueOf(dataSnapshot.child("password").getValue());
                        String username1 = String.valueOf(dataSnapshot.child("username").getValue());


                        fullName.setText(name1);
                        username.setText(username1);
                        emailadd.getEditText().setText(email1);
                        password.getEditText().setText(password1);
                        phoneNo.getEditText().setText(phoneNo1);
                        fullname.getEditText().setText(name1);
                    }
                }
            }
        });
    }



//    public void logoutUser(View view) {
//        AlertDialog.Builder dialog = new AlertDialog.Builder(l.getContext());
//        dialog.setTitle("LOGOUT");
//        dialog.setMessage("Are You Sure You Want To Logout ?");
//
//
//        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                FirebaseAuth.getInstance().signOut();
//                SharedPreferences preferences = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.clear();
//                editor.apply();
//                Intent intent = new Intent(getActivity(),Login.class);
//                startActivity(intent);
//                getActivity().finish();
//
//            }
//        });
//
//        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
}