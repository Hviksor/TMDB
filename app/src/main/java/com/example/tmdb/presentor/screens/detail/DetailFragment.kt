package com.example.tmdb.presentor.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var mBinding: FragmentDetailBinding
    private val binding get() = mBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDetailBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return mBinding.root
    }

}