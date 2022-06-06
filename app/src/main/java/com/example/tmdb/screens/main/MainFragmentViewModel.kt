package com.example.tmdb.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdb.data.retrofit.RetrofitRepository
import com.example.tmdb.data.room.repository.MoviesRepository
import com.example.tmdb.models.MoviesModel
import okhttp3.Response

class MainFragmentViewModel : ViewModel() {
    private val repo = RetrofitRepository()

    val moviesInformation: MutableLiveData<retrofit2.Response<MoviesModel>> = MutableLiveData()

    suspend fun getMoviesModel() {
        moviesInformation.value = repo.getMoviesInformation()
    }
}