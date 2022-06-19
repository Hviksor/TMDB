package com.example.tmdb.domain

import androidx.lifecycle.LiveData
import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.domain.model.MovieModel

class GetMovieFavoriteListUseCase(private val repo: MovieRepositoryImpl) {
    suspend fun getMovieFavoriteList(db: MovieDao) {
        repo.getMovieFavoriteList(db)
    }
}