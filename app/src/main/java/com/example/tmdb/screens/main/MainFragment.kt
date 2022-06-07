package com.example.tmdb.screens.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.EXTRA_MOVIE_INFO
import com.example.tmdb.MAIN
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentMainBinding
import com.example.tmdb.models.MovieItem
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var mbinding: FragmentMainBinding? = null
    private val binding get() = mbinding!!
    private lateinit var rcView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var ctx: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mbinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inite()
    }

    private fun inite() {
        rcView = binding.rcViewMain
        adapter = MainAdapter()
        rcView.adapter = adapter
        viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        viewModel.initDatabase()
        viewModel.viewModelScope.launch {
            viewModel.getMoviesRetrofit()
        }
        viewModel.moviesInformation.observe(viewLifecycleOwner) {
            adapter.submitList(it.body()?.results)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mbinding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_favorite -> {
                MAIN.navController.navigate(R.id.action_mainFragment_to_favoriteFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }

    companion object {
        fun clickMovie(model: MovieItem) {
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_MOVIE_INFO, model)
            MAIN.navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }

    }

}