package com.example.restaurantapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.restaurantapp.R
import com.example.restaurantapp.data.local.RestaurantEntity
import com.example.restaurantapp.ui.RestaurantDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment : Fragment(R.layout.fragment_restaurant_detail) {

    private val viewModel: RestaurantDetailViewModel by viewModels()
    private val args: RestaurantDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logoImageView: ImageView = view.findViewById(R.id.logoImageView)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val typeTextView: TextView = view.findViewById(R.id.typeTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val reviewTextView: TextView = view.findViewById(R.id.reviewTextView)
        val phoneNumberTextView: TextView = view.findViewById(R.id.phoneNumberTextView)
        val addressTextView: TextView = view.findViewById(R.id.addressTextView)
        val hoursTextView: TextView = view.findViewById(R.id.hoursTextView)

        viewModel.getRestaurant(args.restaurantId).observe(viewLifecycleOwner) { restaurant ->
            restaurant?.let {
                bindDetails(restaurant, logoImageView, nameTextView, typeTextView, descriptionTextView, reviewTextView, phoneNumberTextView, addressTextView, hoursTextView)
            }
        }
    }

    private fun bindDetails(
        restaurant: RestaurantEntity,
        logoImageView: ImageView,
        nameTextView: TextView,
        typeTextView: TextView,
        descriptionTextView: TextView,
        reviewTextView: TextView,
        phoneNumberTextView: TextView,
        addressTextView: TextView,
        hoursTextView: TextView
    ) {
        Glide.with(requireContext()).load(restaurant.logo).into(logoImageView)
        nameTextView.text = restaurant.name
        typeTextView.text = restaurant.type
        descriptionTextView.text = restaurant.description
        reviewTextView.text = restaurant.review
        phoneNumberTextView.text = restaurant.phoneNumber
        addressTextView.text = restaurant.address
       // hoursTextView.text = restaurant.hours.toString()
    }
}
