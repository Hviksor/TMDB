package com.example.tmdb.screens.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdb.REALIZATION
import com.example.tmdb.data.retrofit.RetrofitRepository
import com.example.tmdb.data.room.MoviesRoomDataBase
import com.example.tmdb.data.room.repository.MoviesRepositoryRealisation
import com.example.tmdb.models.MoviesModel

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val ctx = application
    private val repoRetrofit = RetrofitRepository()

    val moviesInformation: MutableLiveData<retrofit2.Response<MoviesModel>> = MutableLiveData()


    suspend fun getMoviesRetrofit() {
        try {
            moviesInformation.value = repoRetrofit.getMoviesInformation()
        } catch (e: Exception) {
            Log.e("ERROR", e.message.toString())
        }
    }

    fun initDatabase() {
        val movieDao = MoviesRoomDataBase.getInstanceDb(ctx).getDao()
        REALIZATION = MoviesRepositoryRealisation(movieDao)
    }
}