package com.example.tmdb.domain.useCases

import androidx.lifecycle.LiveData
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.data.MovieRepositoryImpl

class GetMovieListUseCase(private val movieRepositoryImpl: MovieRepositoryImpl) {
    fun getMovieList(): LiveData<MovieModel> {
        TODO()

    }
}