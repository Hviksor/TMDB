package com.example.tmdb.presentor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.retrofit.api.ApiService
import com.example.tmdb.data.retrofit.api.RetrofitInstance
import com.example.tmdb.data.room.MovieRoomDataBase
import com.example.tmdb.data.room.repository.RoomRepositoryImpl
import com.example.tmdb.domain.*
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBInfo
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = MovieRepositoryImpl
    private val movieDao = MovieRoomDataBase.getInstanceDb(application).getDao()
    private val roomRepo = RoomRepositoryImpl(movieDao)
    private val addFavoriteUseCase = AddFavoriteUseCase(repo)
    private val deleteFavoriteUseCase = DeleteFavoriteUseCase(repo)
    private val getFavoriteMovieList = GetFavoriteMovieListUseCase(repo)
    private val getMovieListFromTMDB = GetMovieListFromTMDBUseCase(repo)
    private val getSingleMovieInformFromTMDB = GetSingleMovieInformFromTMDBUseCase(repo)

    init {
        viewModelScope.launch {
            getMovieInfoFromTMDB()
        }

    }

    private val _movieInformationFromTMDB = MutableLiveData<Response<TMDBInfo>>()
    val movieInformationFromTMDB: LiveData<Response<TMDBInfo>>
        get() = _movieInformationFromTMDB

    private val _movieFavoriteList = MutableLiveData<List<MovieModel>>()
    val movieFavoriteList: LiveData<List<MovieModel>>
        get() = _movieFavoriteList

    private var _singleMovieInformation = MutableLiveData<MovieModel>()
    val singleMovieInformation: LiveData<MovieModel>
        get() = _singleMovieInformation


    suspend fun getSingleMovieInfoFromTMDB(movieId: Int) {
        _singleMovieInformation.value = getSingleMovieInformFromTMDB.getMovie(movieId).body()
    }

    private suspend fun getMovieInfoFromTMDB() {
        val isFavoriteMovie = getMovieListFromTMDB.getMovieListFromTMDB()
        _movieInformationFromTMDB.value = isFavoriteMovie
    }


    suspend fun addFavoriteMovie(movieModel: MovieModel) {
        addFavoriteUseCase.addFavoriteMovie(movieModel, roomRepo)
    }

    suspend fun deleteFavoriteMovie(movieModel: MovieModel) {
        deleteFavoriteUseCase.deleteFavoriteMovie(movieModel, roomRepo)
    }

    suspend fun getFavoriteMovieList() {
        _movieFavoriteList.value = roomRepo.getAllFavoriteMovies().value
    }


}