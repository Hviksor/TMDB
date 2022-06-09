package com.example.tmdb.domain.useCases

import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.data.MovieRepositoryImpl

class GetMovieUseCase(private val movieRepositoryImpl: MovieRepositoryImpl) {
    fun getMovie(): MovieModel {
        TODO()
    }
}