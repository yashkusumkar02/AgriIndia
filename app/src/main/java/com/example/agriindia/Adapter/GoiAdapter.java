package com.example.agriindia.Adapter;

import android.animation.RectEvaluator;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriindia.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class GoiAdapter extends RecyclerView.Adapter<GoiAdapter.GoidViewHolder> {

    LayoutInflater layoutInflater;
    List<GoiDetail> goiDetails;


    public GoiAdapter(Context context,List<GoiDetail> details) {
        this.layoutInflater= LayoutInflater.from(context);
        this.goiDetails=details;
    }

    @NonNull
    @Override
    public GoidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.goitem,parent,false);

        return new GoidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoidViewHolder holder, int position) {

        holder.itemName.setText(goiDetails.get(position).getGroceryName());
        holder.itemPlace.setText(goiDetails.get(position).getGroceryPlace());
        holder.itemPrice.setText("₹"+goiDetails.get(position).getGrocerPrice());

        Calendar cal= Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(goiDetails.get(position).getGroceryTime()*1000);
            String date= DateFormat.format("dd/MM/yyyy",cal).toString();
            holder.itemDate.setText(date);


    }

    @Override
    public int getItemCount() {
        return goiDetails.size();
    }


    public static class GoidViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemPlace, itemPrice,itemDate;


        public GoidViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.itemNameView);
            itemPlace= itemView.findViewById(R.id.itemPlace);
            itemPrice=itemView.findViewById(R.id.itemPrice);
            itemDate=itemView.findViewById(R.id.itemDate);
        }
    }


}
