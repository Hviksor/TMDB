package com.example.tmdb.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.data.room.repository.RoomRepositoryImpl
import com.example.tmdb.domain.model.MovieModel

class GetFavoriteMovieListUseCase(private val repo: MovieRepositoryImpl) {
     fun getMovieFavoriteList(db: RoomRepositoryImpl): LiveData<List<MovieModel>> {
        return repo.getFavoriteMovieList(db)
    }
}