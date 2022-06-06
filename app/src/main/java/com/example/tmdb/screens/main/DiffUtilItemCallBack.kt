package com.example.tmdb.screens.main

import androidx.recyclerview.widget.DiffUtil
import com.example.tmdb.models.MovieItem

class DiffUtilItemCallBack : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }
}