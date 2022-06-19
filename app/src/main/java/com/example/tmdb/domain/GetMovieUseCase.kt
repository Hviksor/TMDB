package com.example.tmdb.domain

import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.domain.model.MovieModel

class GetMovieUseCase(private val repo: MovieRepositoryImpl) {
   suspend fun getMovie(movieId: Int): MovieModel {
        TODO()
    }
}