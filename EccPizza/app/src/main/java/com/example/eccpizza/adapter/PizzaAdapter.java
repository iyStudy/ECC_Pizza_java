package com.example.eccpizza.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eccpizza.model.Pizza;
import com.example.eccpizza.R;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private List<Pizza> pizzaList;
    private Context context;

    public PizzaAdapter(Context context, List<Pizza> pizzaList) {
        this.context = context;
        this.pizzaList = pizzaList;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pizza_item, parent, false);
        return new PizzaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);

        Log.d("PizzaAdapter", "Loading data for pizza at position " + position + ": " + pizza.getPname());
        Log.d("PizzaAdapter", "Loading data for pizza at position " + position + ": " + pizza.getPrice());
        Log.d("PizzaAdapter", "Loading data for pizza at position " + position + ": " + pizza.getImage_url());

        holder.pname.setText(pizza.getPname());
        holder.price.setText("￥"+pizza.getPrice());
        Glide.with(context).load(pizza.getImage_url()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView pname, price;
        ImageView image;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            pname = itemView.findViewById(R.id.pizza_name);
            price = itemView.findViewById(R.id.pizza_price);
            image = itemView.findViewById(R.id.pizza_image);
        }
    }
}
