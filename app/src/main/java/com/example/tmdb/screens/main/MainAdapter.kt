package com.example.tmdb.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.BASE_IMG_URL
import com.example.tmdb.R
import com.example.tmdb.databinding.ItemLayoutBinding
import com.example.tmdb.models.MovieItem
import com.squareup.picasso.Picasso

class MainAdapter : ListAdapter<MovieItem, MainAdapter.MainViewHolder>(DiffUtilItemCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        holder.title.text = item.title
        holder.date.text = item.release_date
        Picasso.get()
            .load(BASE_IMG_URL + item.poster_path)
            .resize(300, 300)
            .centerCrop()
            .into(holder.poster)

    }


    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemLayoutBinding.bind(itemView)
        val title = binding.itemTitle
        val date = binding.itemDate
        val poster = binding.itemImage

    }
}