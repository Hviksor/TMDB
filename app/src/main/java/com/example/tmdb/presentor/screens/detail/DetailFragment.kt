package com.example.tmdb.presentor.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tmdb.BASE_IMG_URL
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentDetailBinding
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.presentor.MainActivity
import com.example.tmdb.presentor.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private var movieId: Int = DEFAULT_MOVIE_ID
    private var isFavorite = false
    private lateinit var tempMovieModel: MovieModel
    private lateinit var mBinding: FragmentDetailBinding
    private val binding get() = mBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        parsArgs()
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
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setHasOptionsMenu(true)
        initFields()
    }

    private fun parsArgs() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_MOVIE_ID)) {
            throw RuntimeException("Movie ID is absent")
        }
        val id = args.getInt(EXTRA_MOVIE_ID, DEFAULT_MOVIE_ID)
        if (id > 0) {
            movieId = id
        }
    }

    private fun initFields() {
        viewModel.viewModelScope.launch {
            viewModel.getSingleMovieInfoFromTMDB(movieId)
        }
        viewModel.singleMovieInformation.observe(viewLifecycleOwner) {
            tempMovieModel = it
            binding.tvTitle.text = it.title
            binding.tvDescription.text = it.overview
            binding.tvDate.text = it.releaseDate
            Picasso.get().load(BASE_IMG_URL + it.posterPath)
                .centerCrop()
                .resize(300, 300)
                .into(binding.imgDetail)

        }
        binding.imgDetailFavorite.setOnClickListener {
            isFavorite = if (isFavorite) {
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)

                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    viewModel.deleteFavoriteMovie(tempMovieModel)
                }
                false
            } else {
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    viewModel.addFavoriteMovie(tempMovieModel)
                }
                true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.supportFragmentManager?.popBackStack()
        }
        return true

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