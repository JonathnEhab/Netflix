package com.example.netflix.repository.remote

import com.example.domain.models.models.moviedetails.MovieDetails
import com.example.domain.models.models.playingNow.MovieDbResultNowPlaying
import com.example.domain.models.models.popular.MovieDbResultPopular
import com.example.domain.models.models.upcoming.MovieDbResultUpcoming
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun fetchUpcomingMovies(
        language: String = "en-US",
        page: Int
    ): Flow<MovieDbResultUpcoming>


    suspend fun fetchPopularMovies(
        language: String = "en-US",
        page: Int
    ): Flow<MovieDbResultPopular>

    suspend fun fetchNowPlayingMovies(
        language: String = "en-US",
        page: Int
    ): Flow<MovieDbResultNowPlaying>

    suspend fun fetchMovieDetails(
        movieId: Int
    ): Flow<MovieDetails>

}
