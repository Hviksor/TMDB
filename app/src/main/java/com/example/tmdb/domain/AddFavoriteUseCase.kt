package com.example.tmdb.domain

import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.room.repository.RoomRepositoryImpl
import com.example.tmdb.domain.model.MovieModel

class AddFavoriteUseCase(private val repo: MovieRepositoryImpl) {
    suspend fun addFavoriteMovie(movieModel: MovieModel, db: RoomRepositoryImpl) {

        repo.addFavoriteMovie(movieModel, db)
    }
}