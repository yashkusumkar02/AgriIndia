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
import com.example.agriindia.Fragments.CartProductFragment;
import com.example.agriindia.Fragments.OrderDetailFragment;
import com.example.agriindia.R;
import com.example.agriindia.model.cartModel;
import com.example.agriindia.model.productModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class OrderAdapter extends FirebaseRecyclerAdapter<cartModel,OrderAdapter.myviewHolder> {

    Context context;
    Activity activity;
    public OrderAdapter(@NonNull FirebaseRecyclerOptions<cartModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull cartModel model) {

        holder.title.setText(model.getTitle());
        holder.date.setText(model.getDate());
        holder.orderID.setText(model.getOrderID());
        holder.total.setText(model.getTotal());
        holder.mode.setText(model.getPayment());
        holder.status.setText(model.getStatus());

        Glide.with(holder.image.getContext()).load(model.getPurl()).into(holder.image);


        holder.view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new OrderDetailFragment(model.getTitle())).addToBackStack(null).commit();

            }
        });


    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_order,parent,false);
        return new myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView date,orderID,total,mode,status,title;
        Button view_more;

        CardView card;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            date = itemView.findViewById(R.id.date);
            orderID = itemView.findViewById(R.id.orderID);
            total = itemView.findViewById(R.id.total);
            mode = itemView.findViewById(R.id.mode);
            status = itemView.findViewById(R.id.status);
            view_more = itemView.findViewById(R.id.viewBtn);
            title = itemView.findViewById(R.id.title);
            card = itemView.findViewById(R.id.card);

        }
    }


}
