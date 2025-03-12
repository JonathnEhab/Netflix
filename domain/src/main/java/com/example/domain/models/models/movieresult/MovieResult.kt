package com.example.domain.models.models.movieresult

data class MovieResult(
    val id: Int,
    val original_language: String,
    val original_title: String,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val vote_average: Double
)
