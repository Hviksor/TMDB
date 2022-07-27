package com.example.tmdb.data.room.repository

import androidx.lifecycle.LiveData
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.domain.model.MovieModel

class RoomRepositoryImpl(private val dao: MovieDao) : RoomRepository {
    override suspend fun addFavoriteMovie(movieModel: MovieModel, onSuccess: () -> Unit) {
        dao.insert(movieModel)
        onSuccess
    }

    override suspend fun deleteFavoriteMovie(movieModel: MovieModel, onSuccess: () -> Unit) {
        dao.delete(movieModel)
        onSuccess
    }

    override val favoriteMovie: LiveData<List<MovieModel>>
        get() = dao.getFavoriteMovieList()

    override suspend fun checkIsFavoriteMovie(movieId: Int): LiveData<Boolean> {
        return dao.checkIsFavoriteMovie(movieId)
    }
}