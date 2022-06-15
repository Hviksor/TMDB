package com.example.tmdb.domain.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class TMDBAnswer(
    @SerializedName("results")
    @Expose
    val results: List<MovieModel>? = null


)
