package com.example.restaurantapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.restaurantapp.data.local.RestaurantEntity
import com.example.restaurantapp.repository.RestaurantRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

    private val restaurantId = MutableLiveData<Int>()

    val restaurant: LiveData<RestaurantEntity> = Transformations.switchMap(restaurantId) { id ->
        Transformations.map(repository.getRestaurants()) { restaurants ->
            restaurants.find { it.id == id }
        }
    }

    fun getRestaurant(id: Int): LiveData<RestaurantEntity> {
        restaurantId.value = id
        return restaurant
    }
}
