package com.example.tmdb.presentor

import androidx.recyclerview.widget.DiffUtil
import com.example.tmdb.domain.model.MovieModel

class DiffUtilItemCallBack : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }
}