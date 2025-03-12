package com.example.domain.repository

import com.example.domain.models.models.moviedetails.MovieDetails
import com.example.domain.models.models.playingNow.MovieDbResultNowPlaying
import com.example.domain.models.models.popular.MovieDbResultPopular
import com.example.domain.models.models.upcoming.MovieDbResultUpcoming

interface Repository {
    suspend fun getNowPlayingMovies(): MovieDbResultNowPlaying
    suspend fun getUpComingMovies(): MovieDbResultUpcoming
    suspend fun getPopularMovies(): MovieDbResultPopular
    suspend fun getMovieDetails(movieId :Int):MovieDetails
}