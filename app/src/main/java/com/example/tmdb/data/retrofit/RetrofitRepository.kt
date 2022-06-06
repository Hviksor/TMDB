package com.example.tmdb.data.retrofit

import com.example.tmdb.data.retrofit.api.ApiService
import com.example.tmdb.data.retrofit.api.RetroFitInstance
import com.example.tmdb.models.MovieItem
import com.example.tmdb.models.MoviesModel
import retrofit2.Response

class RetrofitRepository{
     suspend fun getMoviesInformation(): Response<MoviesModel> {
       return RetroFitInstance.api.getMoviesInformation()
    }


}