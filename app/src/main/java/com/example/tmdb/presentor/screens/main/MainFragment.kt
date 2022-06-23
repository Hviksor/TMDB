package com.example.tmdb.presentor.screens.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentMainBinding
import com.example.tmdb.presentor.MainViewModel
import com.example.tmdb.presentor.MovieAdapter
import com.example.tmdb.presentor.screens.detail.DetailFragment
import com.example.tmdb.presentor.screens.favorite.FavoriteFragment
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var mBinding: FragmentMainBinding
    private val binding get() = mBinding
    private lateinit var rcView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        inite()
    }

    private fun inite() {
        rcView = binding.rcView
        movieAdapter = MovieAdapter()
        rcView.adapter = movieAdapter
        onMovieClickListener()
        viewModel.viewModelScope.launch {
            viewModel.getMovieListFromTMDB()
        }
        viewModel.movieInformationFromTMDB.observe(viewLifecycleOwner) {
            Log.e("movieInformationFromTMDB", it.body()?.results.toString())
            movieAdapter.submitList(it.body()?.results)
        }


    }

    private fun onMovieClickListener() {
        movieAdapter.onClickMovie = {

            val fragment = DetailFragment.getDetailFragment(it.id)

            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val fragment = FavoriteFragment()
        return when (item.itemId) {

            R.id.favorite -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, fragment)
                    .commit()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }


}