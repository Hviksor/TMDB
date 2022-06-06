package com.example.tmdb.data.retrofit.api

import com.example.tmdb.models.MovieItem
import com.example.tmdb.models.MoviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular?api_key=cafa669d828b93b4e5f024b227e17c34&language=en-US&page=1")
    suspend fun getMoviesInformation(
        @Query("QUERY_API_KEY") apiKey: String = API_KEY
    ): Response<MoviesModel>

    companion object {

        const val QUERY_API_KEY = "api_key"
        const val API_KEY = "cafa669d828b93b4e5f024b227e17c34"
    }


}