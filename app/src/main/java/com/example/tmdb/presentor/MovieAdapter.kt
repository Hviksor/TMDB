package com.example.tmdb.presentor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.databinding.MovieItemLayoutBinding
import com.example.tmdb.domain.model.MovieModel
import com.squareup.picasso.Picasso

class MovieAdapter : ListAdapter<MovieModel, MovieAdapter.MovieViewHolder>(DiffUtilItemCallBack()) {


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = MovieItemLayoutBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.itemDate.text = item.releaseDate
        holder.binding.itemTitle.text = item.title
        Picasso.get().load(MovieModel.BASE_IMG_URL + item.posterPath)
            .centerCrop()
            .resize(200, 200)
            .into(holder.binding.itemImage)
    }

}