package com.example.tmdb.presentor.screens.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tmdb.databinding.FragmentDetailBinding
import com.example.tmdb.domain.model.TMDBInfo
import com.example.tmdb.presentor.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private var movieId: Int = DEFAULT_MOVIE_ID
    private lateinit var mBinding: FragmentDetailBinding
    private val binding get() = mBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDetailBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseFields()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        inite()

    }

    private fun parseFields() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_MOVIE_ID)) {
            throw RuntimeException("Movie ID is absent")
        }
        val id = args.getInt(EXTRA_MOVIE_ID, DEFAULT_MOVIE_ID)
        Log.e("movieId", id.toString())
        if (id > 0) {
            movieId = id
        }
    }

    private fun inite() {
        viewModel.viewModelScope.launch {

            viewModel.getMovie(movieId)
        }
        viewModel.singleMovieInformation.observe(viewLifecycleOwner) {
            Log.e("singleMovieInformation", it.title.toString())
            binding.tvTitle.text = it.title
            binding.tvDescription.text = it.overview
            binding.tvDate.text = it.releaseDate
            Picasso.get().load(TMDBInfo.BASE_IMG_URL + it.posterPath)
                .centerCrop()
                .resize(300,300)
                .into(binding.imgDetail)

        }

    }


    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val DEFAULT_MOVIE_ID = -1

        fun getDetailFragment(tempMovieId: Int?): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    tempMovieId?.let { putInt(EXTRA_MOVIE_ID, it) }
                }
            }
        }
    }

}