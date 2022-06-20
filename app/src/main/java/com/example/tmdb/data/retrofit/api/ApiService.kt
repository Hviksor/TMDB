package com.example.tmdb.data.retrofit.api

import com.example.tmdb.domain.model.MovieModel
import com.example.tmdb.domain.model.TMDBInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular?")
    suspend fun getTMDBInfo(
        @Query(QUERY_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE,
        @Query(QUERY_PAGE) page: String = PAGE
    ): Response<TMDBInfo>

    @GET("3/movie/{movieId}?")
    suspend fun getSingleMovieInfo(
        @Path("movieId") movieId: Int,
        @Query(QUERY_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE
    ): Response<MovieModel>


    companion object {
        private const val QUERY_API_KEY = "api_key"
        private const val QUERY_LANGUAGE = "language"
        private const val QUERY_PAGE = "page"

        private const val API_KEY = "cafa669d828b93b4e5f024b227e17c34"
        private const val LANGUAGE = "ru"
        private const val PAGE = "1"


    }
}