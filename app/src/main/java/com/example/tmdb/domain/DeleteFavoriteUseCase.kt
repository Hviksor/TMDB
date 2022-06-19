package com.example.tmdb.domain

import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.domain.model.MovieModel

class DeleteFavoriteUseCase(private val repo: MovieRepositoryImpl) {
    suspend fun addFavoriteMovie(movieModel: MovieModel, db: MovieDao) {
        repo.deleteFavoriteMovie(movieModel, db)
    }
}