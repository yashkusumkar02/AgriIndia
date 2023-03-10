package com.example.agriindia.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.agriindia.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link APMCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class APMCFragment extends Fragment {

    private GoiAdapter goiAdapter;
    private RecyclerView recyclerView;
    private List<GoiDetail> goiDetails;
    private LinearLayoutManager linearLayoutManager;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private ProgressBar progressBar2;
    private String  text=null;
    private Boolean isScrolling = false;
    int currentItems, totalItems, scrolledItems;
    int offset = 20;

    private EditText searchText;
    private ImageButton searchButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public APMCFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment APMCFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static APMCFragment newInstance(String param1, String param2) {
        APMCFragment fragment = new APMCFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




//
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        recyclerView.findViewById(R.id.recyclerView);
//        goiDetails = new ArrayList<>();
//        linearLayoutManager = new LinearLayoutManager(getActivity());
//        goiAdapter = new GoiAdapter(getActivity(), goiDetails);
//        progressBar = progressBar.findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.GONE);
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(goiAdapter);
//
//
//        toolbar = toolbar.findViewById(R.id.BarLayout);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Agri GOI");
//
//
//        try {
//            DownloadTask task = new DownloadTask();
//            task.execute("https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=xml&offset=0&limit=20");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        loadData();
//
//
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }


    }




    private void loadData() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }


            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = linearLayoutManager.getChildCount();
                totalItems = linearLayoutManager.getItemCount();
                scrolledItems = linearLayoutManager.findFirstVisibleItemPosition();
                if (isScrolling && (currentItems + scrolledItems) == totalItems) {
                    isScrolling = false;
                    progressBar.setVisibility(View.VISIBLE);
                    fetchData();
                }

            }
        });
    }

    private void fetchData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    APMCFragment.DownloadTask task= new APMCFragment.DownloadTask();
                    task.execute("https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=xml&offset=0&limit=20");
                    offset += 20;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5000);

    }


    @SuppressLint("StaticFieldLeak")
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            URL url;

            HttpURLConnection urlConnection = null;
            String result = "";
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }


        @SuppressLint("SuspiciousIndentation")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String records = "";
                    records = jsonObject.getString("records");
                    JSONArray arr = new JSONArray(records);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject part = arr.getJSONObject(i);
                        GoiDetail details = new GoiDetail();
                        details.setGroceryName(part.getString("commodity"));
                        details.setGroceryPlace(part.getString("district") + "," + part.getString("state"));
                        details.setGrocerPrice(part.getString("modal_price"));
                        details.setGroceryTime(Long.valueOf(part.getString("timestamp")));


                        //for checking api you can use log
                        Log.i("WorkAPI", part.getString("district"));
                        goiDetails.add(details);

                    }
                    goiAdapter = new GoiAdapter(getActivity(), goiDetails);
                    goiAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(goiAdapter);

                    if (totalItems==(currentItems + scrolledItems))
                        recyclerView.scrollToPosition(totalItems - currentItems + 1);
                    progressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();

            }
//            progressBar2.setVisibility(View.GONE);
//            if (goiDetails.isEmpty()){
//                Toast.makeText(getActivity(), "Data not Fetched....Enter First Letter Capital Always", Toast.LENGTH_LONG).show();
//            }

        }



    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a_p_m_c, container, false);


        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        recyclerView = view.findViewById(R.id.recyclerView);
        goiDetails = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        goiAdapter = new GoiAdapter(getActivity(), goiDetails);
        progressBar =  view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(goiAdapter);


        toolbar = view.findViewById(R.id.BarLayout);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Agri GOI");


        try {
            DownloadTask task = new DownloadTask();
            task.execute("https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=xml&offset=0&limit=20");

        } catch (Exception e) {
            e.printStackTrace();
        }
        loadData();


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



        return view;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;

        MenuProvider menuProvider= new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.mainmenu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.searchByDistrict){
                    Intent filterintent = new Intent(getActivity(),APMCFragment.class);
                    startActivity(filterintent);
                }
                return false;
            }
        };
    }
}