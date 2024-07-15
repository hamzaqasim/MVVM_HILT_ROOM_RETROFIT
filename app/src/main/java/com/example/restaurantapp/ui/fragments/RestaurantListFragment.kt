package com.example.restaurantapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R
import com.example.restaurantapp.ui.adapters.RestaurantAdapter
import com.example.restaurantapp.ui.viewmodels.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListFragment : Fragment(R.layout.fragment_restaurant_list) {

    private val viewModel: RestaurantViewModel by viewModels()
    private lateinit var adapter: RestaurantAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RestaurantAdapter(
            onClick = { restaurant ->
                findNavController().navigate(
                    RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment(restaurant.id)
                )
            },
            onEmptyList = {
                Toast.makeText(requireContext(), "The list is empty", Toast.LENGTH_SHORT).show()
            }
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        viewModel.restaurants.observe(viewLifecycleOwner) { restaurants ->
            if (restaurants != null) {
                adapter.submitList(restaurants)
            }
        }
    }
}
