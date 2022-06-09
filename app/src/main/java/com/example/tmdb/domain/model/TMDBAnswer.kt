package com.example.tmdb.domain.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class TMDBAnswer(
    @SerializedName("results")
    @Expose
    private val results: List<MovieModel>? = null


)
