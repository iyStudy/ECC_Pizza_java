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

// "PizzaAdapter"という名前のクラスを定義しています。
// このクラスは、RecyclerViewにピザのリストを表示するためのアダプタークラスです。
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    // ピザの情報リストを保存するための変数
    private List<Pizza> pizzaList;

    // コンテキスト情報を保存するための変数。これは、画像の読み込みなどのために使います。
    private Context context;

    // コンストラクタ：このアダプタクラスを初期化するためのメソッド。
    public PizzaAdapter(Context context, List<Pizza> pizzaList) {
        this.context = context;
        this.pizzaList = pizzaList;
    }

    // 新しいViewHolderを作成するためのメソッド。
    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // `pizza_item.xml`を元に、新しいViewを作成します。
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pizza_item, parent, false);
        return new PizzaViewHolder(itemView);
    }

    // 既存のViewHolderにデータをバインド（設定）するためのメソッド。
    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);

        // デバッグ情報をログに出力。
        Log.d("PizzaAdapter", "Loading data for pizza at position " + position + ": " + pizza.getPname());
        Log.d("PizzaAdapter", "Loading data for pizza at position " + position + ": " + pizza.getPrice());
        Log.d("PizzaAdapter", "Loading data for pizza at position " + position + ": " + pizza.getImage_url());

        // ViewHolderの各Viewに、ピザのデータを設定。
        holder.pname.setText(pizza.getPname());
        holder.price.setText("￥"+pizza.getPrice());
        Glide.with(context).load(pizza.getImage_url()).into(holder.image); // Glideを使って、画像を読み込みます。
    }

    // ピザのリストのサイズ（件数）を返すメソッド。
    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    // "PizzaViewHolder"という名前のクラスを定義しています。
    // このクラスは、RecyclerViewの各アイテムのViewを保持するためのクラスです。
    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView pname, price;  // ピザの名前と価格を表示するためのTextView
        ImageView image;       // ピザの画像を表示するためのImageView

        // コンストラクタ：このViewHolderクラスを初期化するためのメソッド。
        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);

            // itemViewから、各Viewを取得します。
            pname = itemView.findViewById(R.id.pizza_name);
            price = itemView.findViewById(R.id.pizza_price);
            image = itemView.findViewById(R.id.pizza_image);
        }
    }
}
