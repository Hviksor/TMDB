package com.example.tmdb.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentDetailBinding
import com.example.tmdb.databinding.FragmentMainBinding

class DetailFragment : Fragment() {
    private var mbinding: FragmentDetailBinding? = null
    private val binding get() = mbinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mbinding = FragmentDetailBinding.inflate(layoutInflater,container,false)
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