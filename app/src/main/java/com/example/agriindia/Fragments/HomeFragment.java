package com.example.agriindia.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.agriindia.Adapter.HorizontalAdapter;
import com.example.agriindia.Adapter.ProductAdapter;
import com.example.agriindia.R;
import com.example.agriindia.model.productModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ProductAdapter productAdapter;
    RecyclerView recyclerViewProducts;
    ImageView banner;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Brown_Flax_Seeds.jpg/1200px-Brown_Flax_Seeds.jpg");
        mNames.add("Seeds");

        mImageUrls.add("data:image/jpeg;base64,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");
        mNames.add("Plants");

        mImageUrls.add("https://www.eletimes.com/wp-content/uploads/2020/02/robots-in-farming.jpg");
        mNames.add("Methods");

        mImageUrls.add("https://cdn.britannica.com/89/126689-004-D622CD2F/Potato-leaf-blight.jpg");
        mNames.add("Diseases");

        mImageUrls.add("https://images.pexels.com/photos/1132047/pexels-photo-1132047.jpeg?cs=srgb&dl=pexels-jane-doan-1132047.jpg&fm=jpg");
        mNames.add("Fruits");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = view.findViewById(R.id.recHorizontal);
        recyclerView.setLayoutManager(layoutManager);
        HorizontalAdapter adapter = new HorizontalAdapter(getContext(),mImageUrls,mNames);
        recyclerView.setAdapter(adapter);


        banner = view.findViewById(R.id.banner);
        Glide.with(getContext())
                .asBitmap()
                .load("https://www.bpd.gmbh/images/layoutbilder/spektro_boden_diagn.jpg")
                .into(banner);


        recyclerViewProducts = view.findViewById(R.id.recVerticle);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerViewProducts.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<productModel> options =
                new FirebaseRecyclerOptions.Builder<productModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Products"),productModel.class)
                        .build();


        productAdapter = new ProductAdapter(options);
        recyclerViewProducts.setAdapter(productAdapter);

        TextView all;
        all = view.findViewById(R.id.showall);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new ShowAllFragment()).addToBackStack(null).commit();
            }
        });

        ImageView cart = view.findViewById(R.id.cartBtn);
        String username = "Test";
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new CartFragment(username)).addToBackStack(null).commit();

            }
        });
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        productAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        productAdapter.stopListening();
    }
}