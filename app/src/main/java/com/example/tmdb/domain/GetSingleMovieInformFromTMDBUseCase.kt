package com.example.tmdb.domain

import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.domain.model.MovieModel
import retrofit2.Response

class GetSingleMovieInformFromTMDBUseCase(private val repo: MovieRepositoryImpl) {
    suspend fun getMovie(movieId: Int): Response<MovieModel> {
        return repo.getSingleMovieInformFromTMDBUseCase(movieId)
    }
}