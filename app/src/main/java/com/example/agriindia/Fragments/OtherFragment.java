package com.example.agriindia.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agriindia.R;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OtherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OtherFragment extends Fragment {

    TextInputLayout fullname,   userName, emailadd, password, phoneNo;
    TextView username,fullName;

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
        fullName= view.findViewById(R.id.fullnameUser);
        username=view.findViewById(R.id.usernameFull);
        emailadd=view.findViewById(R.id.emailAdd);
        password=view.findViewById(R.id.password);
        phoneNo=view.findViewById(R.id.phoneNo);

        String username1= getActivity().getIntent().getStringExtra("username");
        String name= getActivity().getIntent().getStringExtra("name");
        String email1= getActivity().getIntent().getStringExtra("email");
        String user_password= getActivity().getIntent().getStringExtra("password");
        String user_phonenumber= getActivity().getIntent().getStringExtra("phoneNo");



        fullname.getEditText().setText(name);
        username.setText(username1);
        emailadd.getEditText().setText(email1);
        password.getEditText().setText(user_password);
        phoneNo.getEditText().setText(user_phonenumber);
        fullName.setText(name);


        return view;
    }

    private void showAllUserData() {
            String username= getActivity().getIntent().getStringExtra("username");
//        Intent intent = Intent.ge.getIntent();
//        String user_username= intent.getStringExtra("username");
//        String user_name= intent.getStringExtra("name");
//        String user_email= intent.getStringExtra("email");
//        String user_phonenumber= intent.getStringExtra("phoneNo");
//        String user_password= intent.getStringExtra("password");
//
//        fullname.getEditText().setText(user_name);
//        username.setText(user_username);
//        emailadd.getEditText().setText(user_email);
//        password.getEditText().setText(user_password);
//        phoneNo.getEditText().setText(user_phonenumber);
//        fullName.getEditText().setText(user_name);


    }
}