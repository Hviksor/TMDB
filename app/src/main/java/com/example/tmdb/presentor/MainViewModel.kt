package com.example.tmdb.presentor

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.tmdb.data.MovieRepositoryImpl
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
    private val checkIsFavoriteMovie = CheckIsFavoriteMovie(repo)

    init {
        viewModelScope.launch {
            getMovieInfoFromTMDB()
        }
    }

    var movieFavoriteList: LiveData<List<MovieModel>> = MutableLiveData()
    var checkFavoriteMovie: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    private val _movieInformationFromTMDB = MutableLiveData<Response<TMDBInfo>>()
    val movieInformationFromTMDB: LiveData<Response<TMDBInfo>>
        get() = _movieInformationFromTMDB

    private var _singleMovieInformation = MutableLiveData<MovieModel>()
    val singleMovieInformation: LiveData<MovieModel>
        get() = _singleMovieInformation

    fun getFavoriteMovieList() {
        val result = getFavoriteMovieList.getMovieFavoriteList(roomRepo)
        movieFavoriteList = result
    }

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


    suspend fun checkFavoriteMovie(movieId: Int) {
        val result = checkIsFavoriteMovie.checkIsFavoriteMovie(movieId, roomRepo)
        checkFavoriteMovie.postValue(result)
    }


}