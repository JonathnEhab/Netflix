package com.example.domain.usecase

import com.example.domain.repository.Repository

class GetPopularMovie(private val repository: Repository) {
    suspend operator fun invoke() = repository.getPopularMovies()
}