package com.example.netflix.repository.remote

import com.example.domain.models.models.moviedetails.MovieDetails
import com.example.domain.models.models.playingNow.MovieDbResultNowPlaying
import com.example.domain.models.models.popular.MovieDbResultPopular
import com.example.domain.models.models.upcoming.MovieDbResultUpcoming
import com.example.remote.apiservice.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService):
    RemoteDataSource {
    override suspend fun fetchUpcomingMovies(
        language: String,
        page: Int
    ): Flow<MovieDbResultUpcoming> = flowOf( apiService.fetchUpcomingMovies(language, page))

    override suspend fun fetchPopularMovies(
        language: String,
        page: Int
    ): Flow<MovieDbResultPopular>  = flowOf( apiService.fetchPopularMovies(language, page))

    override suspend fun fetchNowPlayingMovies(
        language: String,
        page: Int
    ): Flow<MovieDbResultNowPlaying> = flowOf( apiService.fetchNowPlayingMovies(language, page))

    override suspend fun fetchMovieDetails(movieId: Int)
    : Flow<MovieDetails> =  flowOf( apiService.fetchMovieDetails(movieId))
}