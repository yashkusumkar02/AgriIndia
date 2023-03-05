package com.example.agriindia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agriindia.Fragments.ArticleCatFragment;
import com.example.agriindia.Fragments.ProductFragment;
import com.example.agriindia.R;
import com.example.agriindia.model.articleModel;
import com.example.agriindia.model.productModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HorAdapter extends FirebaseRecyclerAdapter<articleModel,HorAdapter.myviewHolder> {

    Context context;
    Activity activity;
    public HorAdapter(@NonNull FirebaseRecyclerOptions<articleModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull articleModel model) {

        holder.title.setText(model.getTitle());
        Glide.with(holder.image.getContext()).load(model.getPurl()).into(holder.image);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new ArticleCatFragment(model.getTitle())).addToBackStack(null).commit();

            }
        });

    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_horizontal_row,parent,false);
        return new myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;

        CardView card;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            card = itemView.findViewById(R.id.card);

        }
    }


}
