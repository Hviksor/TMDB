package com.example.tmdb.data.retrofit.api

import com.example.tmdb.domain.model.TMDBInfo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/popular?api_key=cafa669d828b93b4e5f024b227e17c34&language=en-US&page=1")
    suspend fun getTMDBInfo(): Response<TMDBInfo>

}