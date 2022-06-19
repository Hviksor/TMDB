package com.example.tmdb.domain

import androidx.lifecycle.LiveData
import com.example.tmdb.data.room.MovieRoomDataBase
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBInfo
import retrofit2.Response

interface MovieRepository {
    suspend fun deleteFavoriteMovie(movieModel: MovieModel, db: MovieDao)
    suspend fun getMovie(movieId: Int): Response<MovieModel>
    suspend fun getMovieFavoriteList(db: MovieDao): LiveData<List<MovieModel>>
    suspend fun getMovieListFromTMDB(): Response<TMDBInfo>
    suspend fun addFavoriteMovie(movieModel: MovieModel, db: MovieDao)
}