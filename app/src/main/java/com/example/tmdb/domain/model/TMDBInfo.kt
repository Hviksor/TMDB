package com.example.tmdb.domain.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class TMDBInfo(
    @SerializedName("results")
    @Expose
    val results: List<MovieModel>? = null
) {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500/"
    }
}

