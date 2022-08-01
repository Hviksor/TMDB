package com.example.tmdb.data

import androidx.lifecycle.LiveData
import com.example.tmdb.data.retrofit.api.RetrofitInstance
import com.example.tmdb.data.room.repository.RoomRepositoryImpl
import com.example.tmdb.domain.MovieRepository
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBInfo
import retrofit2.Response

object MovieRepositoryImpl : MovieRepository {

    override suspend fun addFavoriteMovie(movieModel: MovieModel, db: RoomRepositoryImpl) {
        db.addFavoriteMovie(movieModel) {}
    }

    override suspend fun checkIsFavoriteMovie(movieId: Int, db: RoomRepositoryImpl): Boolean {
        return db.checkIsFavoriteMovie(movieId)
    }

    override fun getFavoriteMovieList(db: RoomRepositoryImpl): LiveData<List<MovieModel>> {
        return db.allFavoriteMovies
    }

    override suspend fun deleteFavoriteMovie(movieModel: MovieModel, db: RoomRepositoryImpl) {
        db.deleteFavoriteMovie(movieModel) {}
    }

    override suspend fun getSingleMovieInformFromTMDBUseCase(movieId: Int): Response<MovieModel> {
        return RetrofitInstance.api.getSingleMovieInfo(movieId = movieId)
    }


    override suspend fun getMovieListFromTMDB(): Response<TMDBInfo> {
        return RetrofitInstance.api.getTMDBInfo()
    }


}