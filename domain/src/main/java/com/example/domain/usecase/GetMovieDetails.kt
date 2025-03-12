package com.example.domain.usecase

import com.example.domain.models.models.moviedetails.MovieDetails
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieDetails(private val repository: Repository) {
    suspend operator fun invoke(movieId:Int) = repository.getMovieDetails(movieId)
}