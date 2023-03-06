package com.example.agriindia.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.agriindia.R;
import com.example.agriindia.UpdateProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleDetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleDetFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String title;
    public ArticleDetFragment() {
        // Required empty public constructor
    }
    public ArticleDetFragment(String title) {
        this.title = title;
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleDetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleDetFragment newInstance(String param1, String param2) {
        ArticleDetFragment fragment = new ArticleDetFragment();
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
        View view = inflater.inflate(R.layout.fragment_article_det, container, false);

                ImageView image;
                TextView titleT,description;

                image = view.findViewById(R.id.image);
                titleT = view.findViewById(R.id.title);
                description = view.findViewById(R.id.desc);

                DatabaseReference reference;
                reference = FirebaseDatabase.getInstance().getReference("Article");

                try {
                    reference.child("category").child("Diseases").child(title).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().exists()) {

                                    //Toast.makeText(getActivity(), "hii", Toast.LENGTH_SHORT).show();
                                    DataSnapshot dataSnapshot = task.getResult();
                                    String title1 = String.valueOf(dataSnapshot.child("title").getValue());
                                    String purl1 = String.valueOf(dataSnapshot.child("purl").getValue());
                                    String desc1 = String.valueOf(dataSnapshot.child("description").getValue());
                                    //Toast.makeText(getActivity(), title1, Toast.LENGTH_SHORT).show();
                                    titleT.setText(title1);
                                    description.setText(desc1);
                                    Glide.with(image.getContext()).load(purl1).into(image);


                                }
                            }
                        }
                    });

                    try {
                        reference.child("category").child("Fruits").child(title).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {

                                        //Toast.makeText(getActivity(), "hii", Toast.LENGTH_SHORT).show();
                                        DataSnapshot dataSnapshot = task.getResult();
                                        String title1 = String.valueOf(dataSnapshot.child("title").getValue());
                                        String purl1 = String.valueOf(dataSnapshot.child("purl").getValue());
                                        String desc1 = String.valueOf(dataSnapshot.child("description").getValue());
                                        //Toast.makeText(getActivity(), title1, Toast.LENGTH_SHORT).show();
                                        titleT.setText(title1);
                                        description.setText(desc1);
                                        Glide.with(image.getContext()).load(purl1).into(image);


                                    }
                                }
                            }
                        });

                        try {
                            reference.child("category").child("Methods").child(title).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        if (task.getResult().exists()) {

                                            //Toast.makeText(getActivity(), "hii", Toast.LENGTH_SHORT).show();
                                            DataSnapshot dataSnapshot = task.getResult();
                                            String title1 = String.valueOf(dataSnapshot.child("title").getValue());
                                            String purl1 = String.valueOf(dataSnapshot.child("purl").getValue());
                                            String desc1 = String.valueOf(dataSnapshot.child("description").getValue());
                                            //Toast.makeText(getActivity(), title1, Toast.LENGTH_SHORT).show();
                                            titleT.setText(title1);
                                            description.setText(desc1);
                                            Glide.with(image.getContext()).load(purl1).into(image);


                                        }
                                    }
                                }
                            });
                            try {
                                reference.child("category").child("Plants").child(title).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            if (task.getResult().exists()) {

                                                //Toast.makeText(getActivity(), "hii", Toast.LENGTH_SHORT).show();
                                                DataSnapshot dataSnapshot = task.getResult();
                                                String title1 = String.valueOf(dataSnapshot.child("title").getValue());
                                                String purl1 = String.valueOf(dataSnapshot.child("purl").getValue());
                                                String desc1 = String.valueOf(dataSnapshot.child("description").getValue());
                                                //Toast.makeText(getActivity(), title1, Toast.LENGTH_SHORT).show();
                                                titleT.setText(title1);
                                                description.setText(desc1);
                                                Glide.with(image.getContext()).load(purl1).into(image);


                                            }
                                        }
                                    }
                                });

                                try {
                                    reference.child("category").child("Seeds").child(title).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                if (task.getResult().exists()) {

                                                    //Toast.makeText(getActivity(), "hii", Toast.LENGTH_SHORT).show();
                                                    DataSnapshot dataSnapshot = task.getResult();
                                                    String title1 = String.valueOf(dataSnapshot.child("title").getValue());
                                                    String purl1 = String.valueOf(dataSnapshot.child("purl").getValue());
                                                    String desc1 = String.valueOf(dataSnapshot.child("description").getValue());
                                                    //Toast.makeText(getActivity(), title1, Toast.LENGTH_SHORT).show();
                                                    titleT.setText(title1);
                                                    description.setText(desc1);
                                                    Glide.with(image.getContext()).load(purl1).into(image);


                                                }
                                            }
                                        }
                                    });
                                }catch (Exception e){}

                            }catch (Exception e){}

                        }catch (Exception e){}

                    } catch (Exception e){}

                }catch (Exception e){

                }

        return view;
    }
}