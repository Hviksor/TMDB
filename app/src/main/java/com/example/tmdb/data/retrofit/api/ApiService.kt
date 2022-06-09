package com.example.tmdb.data.retrofit.api

import com.example.tmdb.domain.model.TMDBAnswer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular?api_key=cafa669d828b93b4e5f024b227e17c34&language=en-US&page=1")
    suspend fun getInformationFromTMDB(
        @Query(QUERY_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE,
        @Query(QUERY_PAGE) page: String = PAGE
    ): Response<TMDBAnswer>

    companion object {
        private const val QUERY_API_KEY = "api_key"
        private const val QUERY_LANGUAGE = "language"
        private const val QUERY_PAGE = "page"


        private const val API_KEY = "cafa669d828b93b4e5f024b227e17c34"
        private const val LANGUAGE = "en-US"
        private const val PAGE = "1"
    }
}