package com.example.tmdb.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tmdb.BASE_IMG_URL
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentDetailBinding
import com.example.tmdb.databinding.FragmentMainBinding
import com.example.tmdb.models.MovieItem
import com.example.tmdb.screens.main.MainFragment
import com.example.tmdb.screens.main.MainFragment.Companion.EXTRA_MOVIE
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private var mbinding: FragmentDetailBinding? = null
    private val binding get() = mbinding!!
    private var currentMovie: MovieItem? = null

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
        inite()
    }

    private fun inite() {
        binding.tvDate.text = currentMovie?.release_date
        binding.tvTitle.text = currentMovie?.title
        binding.tvDescription.text = currentMovie?.overview
        Picasso.get()
            .load(BASE_IMG_URL + currentMovie?.poster_path)
            .centerCrop()
            .resize(300, 300)
            .into(binding.imgDetail)
    }

    private fun parsFields() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_MOVIE)) {
            throw  RuntimeException("EXTRA_MOVIE is absent")
        }
        currentMovie = args.getSerializable(EXTRA_MOVIE) as MovieItem
    }

}