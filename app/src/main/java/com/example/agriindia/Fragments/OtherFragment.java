package com.example.agriindia.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.agriindia.LoginSignUp.LoginandSignupPage;
import com.example.agriindia.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OtherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OtherFragment extends Fragment {

    TextInputLayout fullname, username, fullName, userName, emailadd, password, phoneNo;

    private FirebaseAuth firebaseAuth;

    private Button logoutbtn;

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

        View view= inflater.inflate(R.layout.fragment_other, container, false);

        fullname=view.findViewById(R.id.fullname);
//        fullName= view.findViewById(R.id.fullnameUser);
//        username=view.findViewById(R.id.usernameFull);
        emailadd=view.findViewById(R.id.emailAdd);
        password=view.findViewById(R.id.password);
        phoneNo=view.findViewById(R.id.phoneNo);
        logoutbtn =(Button) view.findViewById(R.id.logoutButton);

        firebaseAuth =FirebaseAuth.getInstance();
        checkUser();

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                checkUser();
                startActivity(new Intent(getActivity(), LoginandSignupPage.class));
            }
        });
        
        showAllUserData();



        return view;
    }

    private void checkUser() {
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        if (firebaseUser!=null){
            startActivity(new Intent(getActivity(),LoginandSignupPage.class));

        }
        else {
            String email= firebaseUser.getEmail();
            emailadd.getEditText().setText(email);

        }
    }

    private void showAllUserData() {

        Intent intent = getActivity().getIntent();
        String user_username= intent.getStringExtra("username");
        String user_name= intent.getStringExtra("name");
        String user_email= intent.getStringExtra("email");
        String user_phonenumber= intent.getStringExtra("phoneNo");
        String user_password= intent.getStringExtra("password");

        fullname.getEditText().setText(user_name);
//        username.getEditText().setText(user_username);
        emailadd.getEditText().setText(user_email);
        password.getEditText().setText(user_password);
        phoneNo.getEditText().setText(user_phonenumber);
//        fullName.getEditText().setText(user_name);


    }
}