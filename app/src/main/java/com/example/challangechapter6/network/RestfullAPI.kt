package com.example.challangechapter6.network

import com.example.challangechapter6.model.ResponseProductItem
import retrofit2.Call
import retrofit2.http.GET

interface RestfullAPI {
    @GET("products.json?rating_less_than=3.7")
    fun getAllCar(): Call<List<ResponseProductItem>>

    @GET("products.json?rating_greater_than=4.5")
    fun favoriteCar(): Call<List<ResponseProductItem>>
}