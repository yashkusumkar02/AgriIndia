package com.example.agriindia.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.agriindia.MainActivity;
import com.example.agriindia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDetailFragment extends Fragment {

    TextView date, orderID, total, mode, status, titleText, quantity, desc, name, email, address,purl;
    ImageView image;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String title;

    public OrderDetailFragment(String title) {
        this.title = title;
    }

    public OrderDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderDetailFragment newInstance(String param1, String param2) {
        OrderDetailFragment fragment = new OrderDetailFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        //TextView date,orderID,total,mode,status,titleText,quantity,desc,name,email,address;

        date = view.findViewById(R.id.date);
        orderID = view.findViewById(R.id.orderID);
        total = view.findViewById(R.id.total);
        mode = view.findViewById(R.id.mode);
        status = view.findViewById(R.id.status);
        titleText = view.findViewById(R.id.title);
        quantity = view.findViewById(R.id.quantity);
        desc = view.findViewById(R.id.desc);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        address = view.findViewById(R.id.address);

        image = view.findViewById(R.id.image);
        String username= getActivity().getIntent().getStringExtra("username");
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String name1 = String.valueOf(dataSnapshot.child("name").getValue());
                        name.setText(name1);
                        String email1 = String.valueOf(dataSnapshot.child("email").getValue());
                        email.setText(email1);
                        String address1 = String.valueOf(dataSnapshot.child("address").getValue());
                        address.setText(address1);

                    }
                }
            }
        });

        DatabaseReference reference1;
        reference1 = FirebaseDatabase.getInstance().getReference("Order");
        reference1.child(username).child(title).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String orderID1 = String.valueOf(dataSnapshot.child("orderID").getValue());
                        orderID.setText(orderID1);
                        String date1 = String.valueOf(dataSnapshot.child("date").getValue());
                        date.setText(date1);
                        String total1 = String.valueOf(dataSnapshot.child("total").getValue());
                        total.setText(total1);
                        String mode1 = String.valueOf(dataSnapshot.child("payment").getValue());
                        mode.setText(mode1);
                        String status1 = String.valueOf(dataSnapshot.child("status").getValue());
                        status.setText(status1);
                        String titleText1 = String.valueOf(dataSnapshot.child("title").getValue());
                        titleText.setText(titleText1);
                        String quantity1 = String.valueOf(dataSnapshot.child("quantity").getValue());
                        quantity.setText(quantity1);
                        String desc1 = String.valueOf(dataSnapshot.child("desc").getValue());
                        desc.setText(desc1);
                        String purl = String.valueOf(dataSnapshot.child("purl").getValue());
                        Glide.with(image.getContext()).load(purl).into(image);

                    }
                }
            }
        });

        Button cancel = view.findViewById(R.id.cancleBtn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(cancel.getContext());
                dialog.setTitle("Cancel Order");
                dialog.setMessage("Are You Sure You Want To Cancel This Order ?");


                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference reference = firebaseDatabase.getReference("Order");

                        reference.child(username).child(title).removeValue();


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


        return view;
    }
}