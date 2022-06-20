package com.example.tmdb.presentor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.databinding.ItemLayoutBinding
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBInfo
import com.squareup.picasso.Picasso

class MovieAdapter : ListAdapter<MovieModel, MovieAdapter.MainViewHolder>(DiffUtilItemCallBack()) {
    var onClickMovie: ((MovieModel) -> Unit)? = null

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.itemDate.text = item.releaseDate.toString()
        holder.binding.itemTitle.text = item.title.toString()
        Picasso.get()
            .load(TMDBInfo.BASE_IMG_URL + item.posterPath)
            .resize(300, 300)
            .centerCrop()
            .into(holder.binding.itemImage)

        holder.itemView.setOnClickListener {
            onClickMovie?.invoke(item)
        }

    }
}