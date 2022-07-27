package com.example.tmdb.domain

import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.data.room.repository.RoomRepositoryImpl
import com.example.tmdb.domain.model.MovieModel

class DeleteFavoriteUseCase(private val repo: MovieRepositoryImpl) {
    suspend fun deleteFavoriteMovie(movieModel: MovieModel, db: RoomRepositoryImpl) {
        repo.deleteFavoriteMovie(movieModel, db)
    }
}