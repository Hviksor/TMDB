package com.example.tmdb.data

import androidx.lifecycle.LiveData
import com.example.tmdb.data.retrofit.api.RetrofitInstance
import com.example.tmdb.data.room.MovieRoomDataBase
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.domain.MovieRepository
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBInfo
import retrofit2.Response

object MovieRepositoryImpl : MovieRepository {
    override suspend fun addFavoriteMovie(movieModel: MovieModel, db: MovieDao) {
        db.insert(movieModel)
    }

    override suspend fun deleteFavoriteMovie(movieModel: MovieModel, db: MovieDao) {
        db.delete(movieModel)
    }

    override suspend fun getMovie(movieIdd: Int): Response<MovieModel> {
        return RetrofitInstance.api.getSingleMovieInfo(movieId = movieIdd)
    }

    override suspend fun getMovieFavoriteList(db: MovieDao): LiveData<List<MovieModel>> {
        return db.getFavoriteMovieList()
    }

    override suspend fun getMovieListFromTMDB(): Response<TMDBInfo> {
        return RetrofitInstance.api.getTMDBInfo()
    }
}