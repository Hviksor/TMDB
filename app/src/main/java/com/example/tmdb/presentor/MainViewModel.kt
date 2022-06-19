package com.example.tmdb.presentor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.room.MovieRoomDataBase
import com.example.tmdb.domain.AddFavoriteUseCase
import com.example.tmdb.domain.DeleteFavoriteUseCase
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBInfo
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = MovieRepositoryImpl
    private val db = MovieRoomDataBase.getInstanceDb(application).getDao()


    val addFavoriteUseCase = AddFavoriteUseCase(repo)
    val deleteFavoriteUseCase = DeleteFavoriteUseCase(repo)

    val moviesInformation: MutableLiveData<Response<TMDBInfo>> = MutableLiveData()

    suspend fun addFavoriteMovie(movieModel: MovieModel) {
        addFavoriteUseCase.addFavoriteMovie(movieModel, db)
    }

    suspend fun getMoviesInfo() {
        moviesInformation.value = repo.getMovieListFromTMDB()
    }

    suspend fun getFavoriteMovieList() {

    }


}