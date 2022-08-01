package com.example.tmdb.presentor.screens.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.view.menu.MenuAdapter
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentFavoriteBinding
import com.example.tmdb.databinding.FragmentMainBinding
import com.example.tmdb.presentor.MainActivity
import com.example.tmdb.presentor.MainViewModel
import com.example.tmdb.presentor.MovieAdapter
import com.example.tmdb.presentor.screens.detail.DetailFragment
import com.example.tmdb.presentor.screens.favorite.FavoriteFragment
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var mBinding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = mBinding ?: throw RuntimeException("FragmentMainBinding = null")

    private lateinit var rcView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var menuHost: MenuHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuHost = requireActivity()
        (activity as MainActivity).supportActionBar?.title = "Popular Movies"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun initMenu() {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.favorite -> {
                        requireActivity().supportFragmentManager
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_container, FavoriteFragment.getInstanceFragment())
                            .commit()
                        true
                    }
                    else -> false
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        initMenu()
        initFields()
        initItemClickListener()
    }

    private fun initFields() {
        rcView = binding.rcView
        movieAdapter = MovieAdapter()
        rcView.adapter = movieAdapter
        viewModel.movieInformationFromTMDB.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it.body()?.results)
        }
    }

    private fun initItemClickListener() {
        movieAdapter.onClickMovie = {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, DetailFragment.getDetailFragment(it.id))
                .commit()
        }
    }


    companion object {
        fun getInstanceFragment(): MainFragment {
            return MainFragment()
        }
    }


}