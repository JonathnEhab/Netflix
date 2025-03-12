package com.example.domain.usecase

import com.example.domain.models.models.popular.MovieDbResultPopular
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNowPlayingMovies(private val  repository: Repository) {
    suspend operator fun invoke() = repository.getNowPlayingMovies()

}