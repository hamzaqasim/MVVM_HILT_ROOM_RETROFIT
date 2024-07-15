package com.example.restaurantapp.repository

import androidx.lifecycle.LiveData
import com.example.restaurantapp.data.local.RestaurantDao
import com.example.restaurantapp.data.local.RestaurantEntity
import com.example.restaurantapp.data.remote.RestaurantApi

import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: RestaurantApi,
    private val dao: RestaurantDao
) {

    fun getRestaurants(): LiveData<List<RestaurantEntity>> {
        return dao.getAllRestaurants()
    }

    suspend fun fetchAndCacheRestaurants() {
        val response = api.getRestaurants()
        if (response.isSuccessful) {
            response.body()?.let { restaurants ->
                val restaurantEntities = restaurants.map {
                    RestaurantEntity(
                        id = it.id,
                        name = it.name,
                        type = it.type,
                        description = it.description,
                        review = it.review,
                        logo = it.logo,
                        phoneNumber = it.phone_number,
                        address = it.address
                    )
                }
                dao.insertAll(restaurantEntities)
            }
        }
    }
}
