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
import com.example.agriindia.model.productModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {
    TextView title1,price,description,counter;
    String title2,price1,purl1,desc,quntity;

    int count;
    ImageView imageView;
    Button add,minus,cart;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String title;

    public ProductFragment(){

    }
    public ProductFragment(String title) {
        // Required empty public constructor
        this.title = title;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        View view = inflater.inflate(R.layout.fragment_product, container, false);


        title1 = view.findViewById(R.id.title);
        price = view.findViewById(R.id.price);
        description = view.findViewById(R.id.description);
        imageView = view.findViewById(R.id.image);
        add = view.findViewById(R.id.add);
        minus = view.findViewById(R.id.minus);
        cart = view.findViewById(R.id.cartBtnP);
        counter = view.findViewById(R.id.counter);

        //final String username = getActivity().getIntent().getStringExtra("username");

        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("Products");
        reference.child(title).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                         title2 = String.valueOf(dataSnapshot.child("title").getValue());
                         price1 = String.valueOf(dataSnapshot.child("price").getValue());
                         purl1 = String.valueOf(dataSnapshot.child("purl").getValue());
                         desc = String.valueOf(dataSnapshot.child("desc").getValue());

                        title1.setText(title2);
                        price.setText("₹ "+price1);
                        description.setText(desc);
                        Glide.with(getContext())
                                .asBitmap()
                                .load(purl1)
                                .into(imageView);

                    }
                }
            }
        });

        count = 1;
        counter.setText(String.valueOf(count));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                counter.setText(String.valueOf(count));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1){
                    count--;
                    counter.setText(String.valueOf(count));
                }
            }
        });

        final String username = getActivity().getIntent().getStringExtra("username");


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qunt = String.valueOf(count);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference("Cart");
                productModel helper = new productModel(purl1,price1,title2,desc,qunt);

                reference.child(username).child(title2).setValue(helper);

                Toast.makeText(getActivity(), "Data Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });



        return  view;
    }
    private void plus(){

    }
}