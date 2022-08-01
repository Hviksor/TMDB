package com.example.tmdb.presentor.screens.favorite

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentFavoriteBinding
import com.example.tmdb.presentor.MainActivity
import com.example.tmdb.presentor.MainViewModel
import com.example.tmdb.presentor.MovieAdapter
import com.example.tmdb.presentor.screens.detail.DetailFragment
import com.example.tmdb.presentor.screens.main.MainFragment

class FavoriteFragment : Fragment() {
    private var mBinding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = mBinding ?: throw RuntimeException("FragmentFavoriteBinding = null")

    lateinit var viewModel: MainViewModel
    private lateinit var rcView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var menuHost: MenuHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuHost = requireActivity()
        (activity as MainActivity).supportActionBar?.title = "Favorite Movie"
    }

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
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initBackPressAction()
        initFields()
        initItemClickListener()
        initMenu()
    }

    private fun initMenu() {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.findItem(R.id.favorite).isVisible = false
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    android.R.id.home -> requireActivity().supportFragmentManager.popBackStack()
                }
                return true
            }

        })
    }

    private fun initBackPressAction() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().supportFragmentManager.popBackStack()
            }
        })
    }

    private fun initFields() {
        rcView = binding.favoriteRcView
        movieAdapter = MovieAdapter()
        rcView.adapter = movieAdapter
        viewModel.getFavoriteMovieList()
        viewModel.movieFavoriteList.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
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

        fun getInstanceFragment(): FavoriteFragment {
            return FavoriteFragment()
        }
    }

}