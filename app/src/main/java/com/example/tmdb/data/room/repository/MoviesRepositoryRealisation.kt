package com.example.tmdb.data.room.repository

import androidx.lifecycle.LiveData
import com.example.tmdb.data.room.dao.MoviesDao
import com.example.tmdb.models.MovieItem

class MoviesRepositoryRealisation(private val moviesDao: MoviesDao) : MoviesRepository {
    override suspend fun insertMovie(movieItem: MovieItem, onSuccess: () -> Unit) {
        moviesDao.insert(movieItem)
        onSuccess
    }

    override suspend fun deleteMovie(movieItem: MovieItem, onSuccess: () -> Unit) {
        moviesDao.delete(movieItem)
        onSuccess
    }

    override val allMovies: LiveData<List<MovieItem>>
        get() = moviesDao.getAllMovies()
}