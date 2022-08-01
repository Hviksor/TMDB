package com.example.tmdb.presentor.screens.detail

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tmdb.BASE_IMG_URL
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentDetailBinding
import com.example.tmdb.databinding.FragmentFavoriteBinding
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.presentor.MainActivity
import com.example.tmdb.presentor.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private var movieId: Int = DEFAULT_MOVIE_ID
    private var isFavorite = false
    private lateinit var tempMovieModel: MovieModel

    private var mBinding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = mBinding ?: throw RuntimeException("FragmentDetailBinding = null")

    private lateinit var viewModel: MainViewModel
    private lateinit var menuHost: MenuHost
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.title = "Detail Movie Information"
        menuHost = requireActivity()
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
        initFields()
        initMenu()
        initBackPressAction()

        checkIsFavorite()
        setOnClickItem()
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

    private fun setOnClickItem() {
        binding.imgDetailFavorite.setOnClickListener {
            isFavorite = if (!isFavorite) {
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    viewModel.addFavoriteMovie(tempMovieModel)
                }
                true

            } else {
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    viewModel.deleteFavoriteMovie(tempMovieModel)
                }
                false

            }
        }
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

    private fun checkIsFavorite() {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.checkFavoriteMovie(movieId)
        }
        viewModel.checkFavoriteMovie.observe(viewLifecycleOwner) {
            isFavorite = if (it) {
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                true
            } else {

                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                true
            }
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