package com.example.tmdb.screens.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tmdb.databinding.FragmentFavoriteBinding
import com.example.tmdb.databinding.FragmentMainBinding

class FavoriteFragment : Fragment() {
    private var mbinding: FragmentFavoriteBinding? = null
    private val binding get() = mbinding!!
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
        inite()
    }

    private fun inite() {
        TODO("Not yet implemented")
    }
}