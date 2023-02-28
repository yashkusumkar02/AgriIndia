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
import com.example.agriindia.R;
import com.example.agriindia.model.productModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProductAdapter extends FirebaseRecyclerAdapter<productModel,ProductAdapter.myviewHolder> {

    Context context;
    Activity activity;
    public ProductAdapter(@NonNull FirebaseRecyclerOptions<productModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull productModel model) {
        holder.title.setText(model.getTitle());
        holder.price.setText(model.getPrice());
        String url = model.getPurl();
        Glide.with(holder.image.getContext()).load(model.getPurl()).into(holder.image);

    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product_row,parent,false);
        return new myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,price;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);

        }
    }


}
