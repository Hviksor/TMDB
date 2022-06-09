package com.example.tmdb.presentor.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tmdb.data.MovieRepositoryImpl

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val repo = MovieRepositoryImpl
}