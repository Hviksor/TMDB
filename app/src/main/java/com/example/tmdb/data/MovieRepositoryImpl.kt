package com.example.tmdb.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.tmdb.data.retrofit.api.RetrofitInstance
import com.example.tmdb.data.room.MovieRoomDataBase
import com.example.tmdb.data.room.dao.MovieDao
import com.example.tmdb.data.room.repository.RoomRepositoryImpl
import com.example.tmdb.domain.MovieRepository
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBInfo
import retrofit2.Response

object MovieRepositoryImpl : MovieRepository {
    override suspend fun addFavoriteMovie(movieModel: MovieModel, db: RoomRepositoryImpl) {

        db.addFavoriteMovie(movieModel) {}
    }

    override suspend fun checkIsFavoriteMovie(movieId: Int, db: RoomRepositoryImpl): LiveData<Boolean> {
        return db.checkIsFavoriteMovie(movieId)
    }

    override suspend fun deleteFavoriteMovie(movieModel: MovieModel, db: RoomRepositoryImpl) {
        db.deleteFavoriteMovie(movieModel) {}
    }

    override suspend fun getSingleMovieInformFromTMDBUseCase(movieIdd: Int): Response<MovieModel> {
        return RetrofitInstance.api.getSingleMovieInfo(movieId = movieIdd)
    }

    override suspend fun getMovieFavoriteList(db: RoomRepositoryImpl): LiveData<List<MovieModel>> {
        return db.favoriteMovie
    }

//    override suspend fun getMovieFavoriteList(db: MovieDao): LiveData<List<MovieModel>> {
//        return db.getFavoriteMovieList()
//    }

    override suspend fun getMovieListFromTMDB(): Response<TMDBInfo> {
        return RetrofitInstance.api.getTMDBInfo()
    }


}