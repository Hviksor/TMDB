package com.example.tmdb.presentor

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.room.MovieRoomDataBase
import com.example.tmdb.data.room.repository.RoomRepositoryImpl
import com.example.tmdb.domain.*
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = MovieRepositoryImpl
    private val dao = MovieRoomDataBase.getInstanceDB(application).getMovieDao()
    private val db = RoomRepositoryImpl(dao)

    private val addFavoriteUseCase = AddFavoriteUseCase(repo)
    private val deleteFavoriteUseCase = DeleteFavoriteUseCase(repo)
    private val getFavoriteMovieList = GetFavoriteMovieListUseCase(repo)
    private val getMovieListFromTMDB = GetMovieListFromTMDBUseCase(repo)
    private val getSingleMovieInformFromTMDBUseCase = GetSingleMovieInformFromTMDBUseCase(repo)

    init {
        viewModelScope.launch {
            getMovieListFromTMDB()
        }
    }


    private val _movieInformationFromTMDB = MutableLiveData<Response<TMDBInfo>>()
    val movieInformationFromTMDB: LiveData<Response<TMDBInfo>>
        get() = _movieInformationFromTMDB

    private val _movieFavoriteList = MutableLiveData<List<MovieModel>>()
    val movieFavoriteList: LiveData<List<MovieModel>>
        get() = _movieFavoriteList

    private val _singleMovieInformFromTMDBUseCase = MutableLiveData<MovieModel>()
    val singleMovieInformFromTMDBUseCase: LiveData<MovieModel>
        get() = _singleMovieInformFromTMDBUseCase



    suspend fun addFavoriteMovie(movieModel: MovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addFavoriteUseCase.addFavoriteMovie(movieModel, db)
        }
    }

    suspend fun deleteFavoriteMovie(movieModel: MovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteUseCase.deleteFavoriteMovie(movieModel, db)
        }
    }

    fun getFavoriteMovieList() {
        viewModelScope.launch(Dispatchers.IO){
            val list = db.getAllFavoriteMovies()
            Log.e("allMovie", list.value?.size.toString())
            _movieFavoriteList.postValue(list.value)
        }


    }

    private suspend fun getMovieListFromTMDB() {
        val isFavoriteMovie = getMovieListFromTMDB.getMovieListFromTMDB()
        _movieInformationFromTMDB.value = isFavoriteMovie
    }

    suspend fun getSingleMovieInformFromTMDBUseCase(movieId: Int) {
        val item = getSingleMovieInformFromTMDBUseCase.getMovie(movieId)
        _singleMovieInformFromTMDBUseCase.value = item.body()
    }


}