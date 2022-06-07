package com.example.tmdb.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tmdb.REALIZATION
import com.example.tmdb.data.room.repository.MoviesRepositoryRealisation
import com.example.tmdb.models.MovieItem
import com.example.tmdb.models.MoviesModel

class FavoriteFragmentViewModel : ViewModel() {


    fun getAllMovies(): LiveData<List<MovieItem>> {
        return REALIZATION.allMovies
    }
}