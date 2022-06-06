package com.example.tmdb.screens.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentMainBinding
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
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val ctx = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inite()
    }

    private fun inite() {
        rcView = binding.rcViewMain
        adapter = MainAdapter()
        rcView.adapter = adapter
//        rcView.layoutManager = LinearLayoutManager(ctx)
        viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        viewModel.viewModelScope.launch {
            viewModel.getMoviesModel()
        }
        viewModel.moviesInformation.observe(viewLifecycleOwner) {
            adapter.submitList(it.body()?.results)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mbinding = null
    }

}