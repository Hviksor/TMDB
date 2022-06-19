package com.example.tmdb.domain

import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.domain.model.MovieModel

class AddFavoriteUseCase(private val repo: MovieRepositoryImpl) {
    suspend fun addFavoriteMovie(movieModel: MovieModel, db: MovieDao) {
        repo.addFavoriteMovie(movieModel, db)
    }
}