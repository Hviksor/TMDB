package com.example.tmdb.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.REALIZATION
import com.example.tmdb.data.room.repository.MoviesRepositoryRealisation
import com.example.tmdb.models.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    fun insert(movieItem: MovieItem, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.insertMovie(movieItem, onSuccess)
            onSuccess()
        }

    }
    fun delete(movieItem: MovieItem, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.deleteMovie(movieItem, onSuccess)
            onSuccess()
        }

    }
}