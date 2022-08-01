package com.example.tmdb.data.room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    override val allFavoriteMovies: LiveData<List<MovieModel>>
        get() = dao.getFavoriteMovieList()

    override suspend fun checkIsFavoriteMovie(movieId: Int): Boolean {
        return dao.checkFavorite(movieId)
    }

}