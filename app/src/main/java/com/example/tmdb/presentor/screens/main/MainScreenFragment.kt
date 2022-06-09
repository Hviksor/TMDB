package com.example.tmdb.presentor.screens.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tmdb.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment() {
    private lateinit var mbinding: FragmentMainScreenBinding
    private val binding get() = mbinding!!
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
        init()
    }

    private fun init() {
        TODO("Not yet implemented")
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