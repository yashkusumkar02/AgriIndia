package com.example.agriindia.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.agriindia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public String title2,price1,purl1,desc,quntity1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String title;
    public CartProductFragment(String title){
        this.title = title;
    }
    public CartProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartProductFragment newInstance(String param1, String param2) {
        CartProductFragment fragment = new CartProductFragment();
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
        View view = inflater.inflate(R.layout.fragment_cart_product, container, false);
        TextView title1,description,quantity,price,total;
        ImageView image;
        Button order;

        title1 = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        quantity = view.findViewById(R.id.quantity);
        price = view.findViewById(R.id.price);
        total = view.findViewById(R.id.total);

        image = view.findViewById(R.id.image);

        order = view.findViewById(R.id.orderBtn);

        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("Cart");
        reference.child("Test").child(title).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        title2 = String.valueOf(dataSnapshot.child("title").getValue());
                        price1 = String.valueOf(dataSnapshot.child("price").getValue());
                        purl1 = String.valueOf(dataSnapshot.child("purl").getValue());
                        desc = String.valueOf(dataSnapshot.child("desc").getValue());
                        quntity1 = String.valueOf(dataSnapshot.child("quantity").getValue());

                        int i = Integer.parseInt(price1);
                        int j = Integer.parseInt(quntity1);
                        int k = i*j;
                        //Toast.makeText(getContext(),k , Toast.LENGTH_SHORT).show();

                        title1.setText(title2);
                        price.setText("₹ "+price1);
                        description.setText(desc);
                        quantity.setText(quntity1);
                        total.setText(String.valueOf(k));


//                        total.setText(k);
//
                        Glide.with(getContext())
                                .asBitmap()
                                .load(purl1)
                                .into(image);

                    }
                }
            }
        });



        return view;
    }
}