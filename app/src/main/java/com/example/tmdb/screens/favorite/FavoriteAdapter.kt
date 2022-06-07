package com.example.tmdb.screens.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.BASE_IMG_URL
import com.example.tmdb.R
import com.example.tmdb.databinding.ItemLayoutBinding
import com.example.tmdb.models.MovieItem
import com.example.tmdb.screens.main.DiffUtilItemCallBack
import com.squareup.picasso.Picasso

class FavoriteAdapter : ListAdapter<MovieItem, FavoriteAdapter.FavoriteViewHolder>(DiffUtilItemCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = getItem(position)
        holder.title.text = item.title
        holder.date.text = item.release_date
        Picasso.get()
            .load(BASE_IMG_URL + item.poster_path)
            .resize(300, 300)
            .centerCrop()
            .into(holder.poster)

    }


    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemLayoutBinding.bind(itemView)
        val title = binding.itemTitle
        val date = binding.itemDate
        val poster = binding.itemImage

    }

    override fun onViewAttachedToWindow(holder: FavoriteViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            FavoriteFragment.clickMovieOnFavoriteFragment(getItem(holder.adapterPosition))
        }
    }

    override fun onViewDetachedFromWindow(holder: FavoriteViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }
}