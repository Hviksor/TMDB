package com.example.tmdb.data.retrofit

import com.example.tmdb.data.retrofit.api.ApiService
import com.example.tmdb.data.retrofit.api.RetrofitInstance
import com.example.tmdb.domain.model.TMDBAnswer
import retrofit2.Response

class ApiRepository {
    suspend fun getInformationFromTMDB(): Response<TMDBAnswer> {
        return RetrofitInstance.api.getInformationFromTMDB()
    }
}