package com.example.eccpizza;

import com.example.eccpizza.model.Pizza;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("pizzaDB.php")
    Call<List<Pizza>> getPizzas();
}
