package com.example.tmdb.domain.useCases

import androidx.lifecycle.LiveData
import com.example.tmdb.domain.model.MovieModel

interface MovieRepository {
    fun editMovie(movieModel: MovieModel)
    fun getMovie(): MovieModel
    fun getMovieList(): LiveData<MovieModel>
}