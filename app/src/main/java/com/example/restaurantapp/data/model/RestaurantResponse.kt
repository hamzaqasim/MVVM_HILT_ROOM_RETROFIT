package com.example.restaurantapp.data.model

data class RestaurantResponse(
    val id: Int,
    val uid: String,
    val name: String,
    val type: String,
    val description: String,
    val review: String,
    val logo: String,
    val phone_number: String,
    val address: String,
    val hours: Hours
)

data class Hours(
    val monday: Day,
    val tuesday: Day,
    val wednesday: Day,
    val thursday: Day,
    val friday: Day,
    val saturday: Day,
    val sunday: Day
)

data class Day(
    val opens_at: String,
    val closes_at: String,
    val is_closed: Boolean
)
