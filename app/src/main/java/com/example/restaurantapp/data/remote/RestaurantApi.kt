package com.example.restaurantapp.data.remote


import com.example.restaurantapp.data.model.RestaurantResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantApi {

    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurants(): Response<List<RestaurantResponse>>
}
