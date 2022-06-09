package com.example.tmdb.data

import androidx.lifecycle.LiveData
import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.useCases.MovieRepository

object MovieRepositoryImpl:MovieRepository {

    override fun editMovie(movieModel: MovieModel) {
        TODO("Not yet implemented")
    }

    override fun getMovie(): MovieModel {
        TODO("Not yet implemented")
    }

    override fun getMovieList(): LiveData<MovieModel> {
        TODO("Not yet implemented")
    }
}