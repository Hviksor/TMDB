package com.example.tmdb.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.*
import com.example.tmdb.databinding.FragmentDetailBinding
import com.example.tmdb.models.MovieItem
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private var mbinding: FragmentDetailBinding? = null
    private val binding get() = mbinding!!
    private var currentMovie: MovieItem? = null
    private var isFavorite: Boolean = false
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mbinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        parsFields()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        inite()
    }

    private fun inite() {
        val valueBool = SaveShared.getFavorite(MAIN, currentMovie?.id.toString())
        if (isFavorite == valueBool) {
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        } else {
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
        binding.tvDate.text = currentMovie?.release_date
        binding.tvTitle.text = currentMovie?.title
        binding.tvDescription.text = currentMovie?.overview
        binding.imgDetailFavorite.setOnClickListener {
            isFavorite = if (isFavorite == valueBool) {
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                SaveShared.setFavorite(MAIN, currentMovie?.id.toString(), true)
                currentMovie?.let {
                    viewModel.insert(it) {}
                }
                true
            } else {
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                SaveShared.setFavorite(MAIN, currentMovie?.id.toString(), false)
                currentMovie?.let {
                    viewModel.delete(it) {}
                }
                false
            }
        }
        Picasso.get()
            .load(BASE_IMG_URL + currentMovie?.poster_path)
            .centerCrop()
            .resize(300, 300)
            .into(binding.imgDetail)

    }

    private fun parsFields() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_MOVIE_INFO)) {
            throw  RuntimeException("EXTRA_MOVIE is absent")
        }
        currentMovie = args.getSerializable(EXTRA_MOVIE_INFO) as MovieItem
    }

}