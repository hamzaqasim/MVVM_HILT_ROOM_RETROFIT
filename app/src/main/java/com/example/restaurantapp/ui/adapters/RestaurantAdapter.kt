package com.example.restaurantapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurantapp.R
import com.example.restaurantapp.data.local.RestaurantEntity


class RestaurantAdapter(
    private val onClick: (RestaurantEntity) -> Unit,
    private val onEmptyList: () -> Unit
) : ListAdapter<RestaurantEntity, RestaurantAdapter.RestaurantViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RestaurantEntity>() {
            override fun areItemsTheSame(oldItem: RestaurantEntity, newItem: RestaurantEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RestaurantEntity, newItem: RestaurantEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<RestaurantEntity>?) {
        super.submitList(list)
        if (list.isNullOrEmpty()) {
            onEmptyList()
        }
    }

    class RestaurantViewHolder(
        itemView: View,
        private val onClick: (RestaurantEntity) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val logoImageView: ImageView = itemView.findViewById(R.id.logoImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val reviewTextView: TextView = itemView.findViewById(R.id.reviewTextView)
        private val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)

//        fun bind(restaurant: RestaurantEntity) {
//            Glide.with(itemView.context).load(restaurant.logo).into(logoImageView)
//            nameTextView.text = restaurant.name
//            reviewTextView.text = restaurant.review
//            typeTextView.text = restaurant.type
//            itemView.setOnClickListener { onClick(restaurant) }
//        }

        fun bind(restaurant: RestaurantEntity) {
          //  Log.d("RestaurantAdapter", "Binding restaurant: ${restaurant.name}")
            Glide.with(itemView.context).load(restaurant.logo).into(logoImageView)
            nameTextView.text = restaurant.name
            reviewTextView.text = restaurant.review
            typeTextView.text = restaurant.type
            itemView.setOnClickListener { onClick(restaurant) }
        }

    }

}
