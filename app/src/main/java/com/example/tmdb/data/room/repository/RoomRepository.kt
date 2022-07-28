package com.example.tmdb.data.room.repository

import androidx.lifecycle.LiveData
import com.example.tmdb.domain.model.MovieModel

interface RoomRepository {
    suspend fun addFavoriteMovie(movieModel: MovieModel, onSuccess: () -> Unit)
    suspend fun deleteFavoriteMovie(movieModel: MovieModel, onSuccess: () -> Unit)
//    val allMovie: LiveData<List<MovieModel>>
    suspend fun getAllFavoriteMovies(): LiveData<List<MovieModel>>
    suspend fun checkIsFavoriteMovie(movieId: Int): LiveData<Boolean>

}