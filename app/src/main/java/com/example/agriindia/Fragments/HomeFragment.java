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
import com.example.agriindia.Adapter.HorAdapter;
import com.example.agriindia.Adapter.HorizontalAdapter;
import com.example.agriindia.Adapter.ProductAdapter;
import com.example.agriindia.R;
import com.example.agriindia.model.articleModel;
import com.example.agriindia.model.productModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    String username;
    ProductAdapter productAdapter;

    HorAdapter horAdapter;
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


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = view.findViewById(R.id.recHorizontal);
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<articleModel> options1 =
                new FirebaseRecyclerOptions.Builder<articleModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Article").child("photo"),articleModel.class)
                        .build();


        horAdapter = new HorAdapter(options1);
        recyclerView.setAdapter(horAdapter);


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

        username = getActivity().getIntent().getStringExtra("username");

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
        horAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        productAdapter.stopListening();
        horAdapter.stopListening();
    }
}