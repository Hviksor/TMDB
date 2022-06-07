package com.example.tmdb.screens.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.EXTRA_MOVIE_INFO
import com.example.tmdb.MAIN
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentFavoriteBinding
import com.example.tmdb.databinding.FragmentMainBinding
import com.example.tmdb.models.MovieItem

class FavoriteFragment : Fragment() {
    private var mbinding: FragmentFavoriteBinding? = null
    private val binding get() = mbinding!!
    lateinit var viewModel: FavoriteFragmentViewModel
    lateinit var favoriteAdapter: FavoriteAdapter
    lateinit var rcView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mbinding = FragmentFavoriteBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FavoriteFragmentViewModel::class.java]
        inite()
    }

    private fun inite() {
        rcView = binding.rcViewFavorite
        favoriteAdapter = FavoriteAdapter()
        rcView.adapter = favoriteAdapter
        viewModel.getAllMovies().observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(it.asReversed())
        }

    }

    companion object {
        fun clickMovieOnFavoriteFragment(model: MovieItem) {
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_MOVIE_INFO, model)
            MAIN.navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }

    }
}