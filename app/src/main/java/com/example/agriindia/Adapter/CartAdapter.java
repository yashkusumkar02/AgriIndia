package com.example.agriindia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agriindia.Fragments.CartProductFragment;
import com.example.agriindia.Fragments.ProductFragment;
import com.example.agriindia.R;
import com.example.agriindia.model.productModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.agriindia.MainActivity;

public class CartAdapter extends FirebaseRecyclerAdapter<productModel,CartAdapter.myviewHolder> {

    Context context;
    Activity activity;
    public CartAdapter(@NonNull FirebaseRecyclerOptions<productModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull productModel model) {

        holder.title.setText(model.getTitle());
        holder.price.setText("₹"+model.getPrice());
        holder.quantity.setText(model.getQuantity());
//        int qua = Integer.parseInt(model.getQuantity());
//        int pr = Integer.parseInt(model.getPrice());
        holder.total.setText("₹"+model.getPrice());

        Glide.with(holder.image.getContext()).load(model.getPurl()).into(holder.image);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new CartProductFragment(model.getTitle())).addToBackStack(null).commit();

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Delete");
                dialog.setMessage("Are You Sure You Want To Remove This Item ?");


                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity mainActivity = new MainActivity();
                        String username = mainActivity.username;
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference reference = firebaseDatabase.getReference("Cart");

                        reference.child(username).child(model.getTitle()).removeValue();


                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();


            }
        });

    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cart_row,parent,false);
        return new myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,price,total,quantity;
        ImageView delete;

        CardView card;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);
            total = itemView.findViewById(R.id.total);
            card = itemView.findViewById(R.id.card);
            quantity = itemView.findViewById(R.id.quantity);
            delete = itemView.findViewById(R.id.delete);
        }
    }


}
