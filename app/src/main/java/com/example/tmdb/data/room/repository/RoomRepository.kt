package com.example.tmdb.data.room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.domain.model.MovieModel

interface RoomRepository {
    suspend fun addFavoriteMovie(movieModel: MovieModel, onSuccess: () -> Unit)
    suspend fun deleteFavoriteMovie(movieModel: MovieModel, onSuccess: () -> Unit)
    val allFavoriteMovies: LiveData<List<MovieModel>>
    suspend fun checkIsFavoriteMovie(movieId: Int): Boolean

}