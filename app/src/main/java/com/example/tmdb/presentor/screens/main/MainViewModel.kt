package com.example.tmdb.presentor.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.data.retrofit.RetrofitRepository
import com.example.tmdb.domain.model.TMDBInfo
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val retrofitRepo = RetrofitRepository()
    val moviesInformation: MutableLiveData<Response<TMDBInfo>> = MutableLiveData()



    suspend fun getMoviesInfo() {
        moviesInformation.value = retrofitRepo.getMovieInformation()
    }
}