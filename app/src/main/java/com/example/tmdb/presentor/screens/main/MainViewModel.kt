package com.example.tmdb.presentor.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.MovieRepositoryImpl
import com.example.tmdb.data.retrofit.ApiRepository
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBAnswer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val apiRepo = ApiRepository()
    val movieInformation: MutableLiveData<Response<TMDBAnswer>> = MutableLiveData<Response<TMDBAnswer>>()

    init {
        fun getTMDBInformation() {
            viewModelScope.launch {
                movieInformation.value = apiRepo.getInformationFromTMDB()
            }
        }

    }


}