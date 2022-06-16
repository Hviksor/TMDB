package com.example.tmdb.data.retrofit

import com.example.tmdb.data.retrofit.api.ApiService
import com.example.tmdb.data.retrofit.api.RetrofitInstance
import com.example.tmdb.domain.model.TMDBInfo
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovieInformation(): Response<TMDBInfo> {
        return RetrofitInstance.api.getTMDBInfo()

    }
}