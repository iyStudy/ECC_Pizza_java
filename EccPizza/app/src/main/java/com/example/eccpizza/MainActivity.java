package com.example.eccpizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.eccpizza.adapter.PizzaAdapter;
import com.example.eccpizza.model.Pizza;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// MainActivityという名前のクラスを定義しています。
// このクラスは、アプリが起動したときのメインの画面の動作を制御するためのクラスです。
public class MainActivity extends AppCompatActivity {

    // リスト表示に使われるRecyclerViewの定義
    private RecyclerView recyclerView;

    // RecyclerViewにピザのデータを表示するためのアダプタ
    private PizzaAdapter adapter;

    // ピザのデータのリストを保存するための変数
    private List<Pizza> pizzaList = new ArrayList<>();

    // アクティビティが作成されたときに最初に実行されるメソッド
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // activity_main.xmlをこのアクティビティのレイアウトとして設定します。
        setContentView(R.layout.activity_main);

        // 上部のツールバーを取得して設定します。
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ECC Pizza Menu");

        // RecyclerViewの初期設定
        recyclerView = findViewById(R.id.rv_menu_list);
        // RecyclerViewに2列のグリッドレイアウトを設定します。
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        // PizzaAdapterを作成し、RecyclerViewのアダプタとして設定します。
        adapter = new PizzaAdapter(this, pizzaList);
        recyclerView.setAdapter(adapter);

        // Retrofitを用いて、Web APIからデータを取得するための設定
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://click.ecc.ac.jp/ecc/yishida/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // ApiServiceというインターフェースをもとに、APIを呼び出すためのインスタンスを作成
        ApiService service = retrofit.create(ApiService.class);

        // ピザのデータを取得するAPIを非同期で呼び出します。
        service.getPizzas().enqueue(new Callback<List<Pizza>>() {

            // APIのレスポンスが成功したときの動作を定義するメソッド
            @Override
            public void onResponse(Call<List<Pizza>> call, Response<List<Pizza>> response) {
                if (response.isSuccessful()) {
                    // レスポンスから取得したピザのリストデータを追加します。
                    pizzaList.addAll(response.body());
                    // データが更新されたことをRecyclerViewのアダプタに通知します。
                    adapter.notifyDataSetChanged();
                }
            }

            // APIの呼び出しに失敗したときの動作を定義するメソッド
            @Override
            public void onFailure(Call<List<Pizza>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed :" +t , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
