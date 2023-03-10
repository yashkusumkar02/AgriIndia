package com.example.agriindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.agriindia.Adapter.GoiAdapter;
import com.example.agriindia.Adapter.GoiDetail;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class APMC extends AppCompatActivity {

    private GoiAdapter goiAdapter;
    private RecyclerView recyclerView;
    private List<GoiDetail> goiDetails;
    private LinearLayoutManager linearLayoutManager;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private ProgressBar progressBar2;
    private Boolean isScrolling = true;
    int currentItems, totalItems, scrolledItems;
    int offset = 20;
    private EditText searchText;
    private ImageButton searchButton;
    private String text= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apmc);


        APMC.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        toolbar=findViewById(R.id.BarLayout);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Agri India GOI");

        progressBar= findViewById(R.id.progressBar6);
        progressBar2= findViewById(R.id.progressBar7);
        progressBar.setVisibility(View.GONE);
        progressBar2.setVisibility(View.GONE);


        recyclerView=findViewById(R.id.filterRecyler);
        goiDetails = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        goiAdapter = new GoiAdapter(APMC.this, goiDetails);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(goiAdapter);


        searchText = findViewById(R.id.searchByDistrict);
        searchButton=  findViewById(R.id.searchButton);

        searchText.requestFocus();
        try {
            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar2.setVisibility(View.VISIBLE);
                    text= String.valueOf(searchText.getText());
                    offset=20;

                    DownloadTask task= new DownloadTask();
                    task.execute("https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=xml&offset=20&limit=10");
                }
            });

            loadData();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

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
            if (s!=null) {
                try {
                    JSONObject jsonObject= new JSONObject(s);
                    String records="";
                    records = jsonObject.getString("records");
                    JSONArray arr=new JSONArray(records);
                    for (int i=0 ; i<arr.length(); i++) {
                        JSONObject part= arr.getJSONObject(i);
                        GoiDetail details=new GoiDetail();
                        details.setGroceryName(part.getString("commodity"));
                        details.setGroceryPlace(part.getString("district")+","+part.getString("state"));
                        details.setGroceryPrice(part.getString("model_price"));
                        details.setGroceryTime(Long.valueOf(part.getString("timestamp")));


                        //for checking api we can use log
                        Log.i("WorkAPI",part.getString("district"));
                        goiDetails.add(details);

                    }
                    goiAdapter = new GoiAdapter(APMC.this,goiDetails);
                    goiAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(goiAdapter);

                    if (totalItems==(currentItems + scrolledItems))
                    recyclerView.scrollToPosition(totalItems - currentItems+1);
                    progressBar.setVisibility(View.GONE);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else  {

                Toast.makeText(APMC.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
            progressBar2.setVisibility(View.GONE);
            if (goiDetails.isEmpty()){
                Toast.makeText(APMC.this, "Data not fetched... Enter first Letter Capital Always", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void loadData() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = linearLayoutManager.getChildCount();
                totalItems = linearLayoutManager.getItemCount();
                scrolledItems= linearLayoutManager.findFirstVisibleItemPosition();
                if (isScrolling &&(currentItems+scrolledItems)== totalItems){
                    isScrolling=false;
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
                    DownloadTask downloadTask = new DownloadTask();
                    downloadTask.execute("https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=xml&offset=20&limit=10");
                    offset+=20;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },5000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId()== R.id.sortByPrice){

            if (goiDetails.isEmpty()){
                Toast.makeText(this, "No data for sorting", Toast.LENGTH_SHORT).show();
            }
            else {
                Collections.sort(goiDetails, new Comparator<GoiDetail>() {
                    @Override
                    public int compare(GoiDetail o1, GoiDetail o2) {
                        return Integer.valueOf(o1.getGroceryPrice()).compareTo(Integer.valueOf(o2.getGroceryPrice()));
                    }
                });
                goiAdapter.notifyDataSetChanged();
            }
        }
        else if (item.getItemId()== R.id.sortByDate){

            if (goiDetails.isEmpty()){
                Toast.makeText(this, "No data Available...!", Toast.LENGTH_SHORT).show();
            }
            else {
                Collections.sort(goiDetails, new Comparator<GoiDetail>() {
                    @Override
                    public int compare(GoiDetail o1, GoiDetail o2) {
                        return Integer.compare(Math.toIntExact(o1.getGroceryTime()),Math.toIntExact(o2.getGroceryTime()));
                    }
                });
                goiAdapter.notifyDataSetChanged();
            }
        }
        else if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return true;

    }
}