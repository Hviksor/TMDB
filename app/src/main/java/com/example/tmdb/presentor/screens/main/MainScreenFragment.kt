package com.example.tmdb.presentor.screens.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.databinding.FragmentMainScreenBinding
import com.example.tmdb.presentor.MovieAdapter

class MainScreenFragment : Fragment() {
    private lateinit var mbinding: FragmentMainScreenBinding
    private val binding get() = mbinding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var rcView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mbinding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        init()
    }

    private fun init() {

        rcView = binding.rcView
        movieAdapter = MovieAdapter()
        rcView.adapter = movieAdapter

        viewModel.movieInformation.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it.body()?.results)
        }
    }


    companion object {
        fun getInstanceMainFragment(context: Context): MainScreenFragment {
            return MainScreenFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}