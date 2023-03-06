package com.example.agriindia.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriindia.Adapter.ArticleAdapter;
import com.example.agriindia.Adapter.ProductAdapter;
import com.example.agriindia.R;
import com.example.agriindia.model.articleModel;
import com.example.agriindia.model.productModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleCatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleCatFragment extends Fragment {

    ArticleAdapter adapter;
    RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String title;
    public ArticleCatFragment() {
        // Required empty public constructor
    }

    public ArticleCatFragment(String title){
        this.title = title;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleCatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleCatFragment newInstance(String param1, String param2) {
        ArticleCatFragment fragment = new ArticleCatFragment();
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
        View view = inflater.inflate(R.layout.fragment_article_cat, container, false);

        recyclerView = view.findViewById(R.id.recArticle);
        TextView titleT = view.findViewById(R.id.title);

        titleT.setText(title);

       // Toast.makeText(getActivity(), title, Toast.LENGTH_SHORT).show();


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<articleModel> options =
                new FirebaseRecyclerOptions.Builder<articleModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Article").child("category").child(title),articleModel.class)
                        .build();

        Bundle args = new Bundle();
        args.putString("index", title);
        getParentFragmentManager().setFragmentResult("index1",args);

        adapter = new ArticleAdapter(options);
        recyclerView.setAdapter(adapter);

        ArticleCatFragment f = new ArticleCatFragment();



//        f.setArguments(args);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();

    }
}