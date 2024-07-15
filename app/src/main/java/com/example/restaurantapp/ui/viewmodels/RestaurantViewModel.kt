package com.example.restaurantapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.data.local.RestaurantEntity
import com.example.restaurantapp.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

    val restaurants: LiveData<List<RestaurantEntity>> = repository.getRestaurants()

    init {
        viewModelScope.launch {
            repository.fetchAndCacheRestaurants()
        }
    }
}
