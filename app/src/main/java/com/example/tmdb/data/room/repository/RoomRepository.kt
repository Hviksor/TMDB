package com.example.tmdb.data.room.repository

import androidx.lifecycle.LiveData
import com.example.tmdb.domain.model.MovieModel

interface RoomRepository {
    suspend fun addFavoriteMovie(movieModel: MovieModel, onSuccess: () -> Unit)
    suspend fun deleteFavoriteMovie(movieModel: MovieModel, onSuccess: () -> Unit)
    val favoriteMovie: LiveData<List<MovieModel>>

}