package com.example.agriindia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agriindia.Fragments.OrderDetailFragment;
import com.example.agriindia.R;
import com.example.agriindia.model.cartModel;
import com.example.agriindia.model.postModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PostAdapter extends FirebaseRecyclerAdapter<postModel,PostAdapter.myviewHolder> {

    Context context;
    Activity activity;
    public PostAdapter(@NonNull FirebaseRecyclerOptions<postModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull postModel model) {

        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDescription());
        holder.name.setText(model.getName());
        Glide.with(holder.image.getContext()).load(model.getPurl()).into(holder.image);

    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_post,parent,false);
        return new myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,name,desc;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.description);

        }
    }


}
