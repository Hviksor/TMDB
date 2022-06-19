package com.example.tmdb.presentor.screens.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.databinding.FragmentFavoriteBinding
import com.example.tmdb.presentor.MovieAdapter

class FavoriteFragment : Fragment() {
    private lateinit var mBinding: FragmentFavoriteBinding
    private val binding get() = mBinding!!
    private lateinit var rcView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inite()
    }

    private fun inite() {
        rcView = binding.favoriteRcView
        movieAdapter = MovieAdapter()
        rcView.adapter = movieAdapter
    }
}