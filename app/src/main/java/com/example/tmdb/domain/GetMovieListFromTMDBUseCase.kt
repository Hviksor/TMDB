package com.example.tmdb.domain

import androidx.lifecycle.LiveData
import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBInfo
import retrofit2.Response

class GetMovieListFromTMDBUseCase(private val repo: MovieRepositoryImpl) {
    suspend fun getMovieListFromTMDB(): Response<TMDBInfo> {
        return repo.getMovieListFromTMDB()
    }
}