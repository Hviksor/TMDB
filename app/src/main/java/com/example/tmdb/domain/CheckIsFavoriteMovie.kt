package com.example.tmdb.domain

import androidx.lifecycle.LiveData
import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.data.room.repository.RoomRepositoryImpl
import com.example.tmdb.domain.model.MovieModel
import retrofit2.Response

class CheckIsFavoriteMovie(private val repo: MovieRepositoryImpl) {
    suspend fun checkIsFavoriteMovie(movieId: Int, db: RoomRepositoryImpl): Boolean {
        return repo.checkIsFavoriteMovie(movieId, db)
    }
}