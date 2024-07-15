package com.example.restaurantapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RestaurantEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val description: String,
    val review: String,
    val logo: String,
    val phoneNumber: String,
    val address: String
)