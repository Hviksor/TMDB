package com.example.tmdb.presentor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.room.MovieRoomDataBase
import com.example.tmdb.domain.*
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBInfo
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = MovieRepositoryImpl
    private val db = MovieRoomDataBase.getInstanceDb(application).getDao()

    private val addFavoriteUseCase = AddFavoriteUseCase(repo)
    private val deleteFavoriteUseCase = DeleteFavoriteUseCase(repo)
    private val getFavoriteMovieList = GetFavoriteMovieListUseCase(repo)
    private val getMovieListFromTMDB = GetMovieListFromTMDBUseCase(repo)
    private val getMovieUseCase = GetMovieUseCase(repo)


    private val _movieInformationFromTMDB = MutableLiveData<Response<TMDBInfo>>()
    val movieInformationFromTMDB: LiveData<Response<TMDBInfo>>
        get() = _movieInformationFromTMDB

    private val _singleMovieInformation = MutableLiveData<MovieModel>()
    val singleMovieInformation: LiveData<MovieModel>
        get() = _singleMovieInformation


    suspend fun addFavoriteMovie(movieModel: MovieModel) {
        addFavoriteUseCase.addFavoriteMovie(movieModel, db)
    }

    suspend fun deleteFavoriteMovie() {
        TODO()
    }

    suspend fun getFavoriteMovieList() {
        TODO()
    }

    suspend fun getMovieListFromTMDB() {
        _movieInformationFromTMDB.value = repo.getMovieListFromTMDB()
    }

    suspend fun getMovie(movieId: Int) {
        val item = getMovieUseCase.getMovie(movieId)
        _singleMovieInformation.value = item.body()
    }




}