package com.example.netflix.repository.room

import com.example.domain.models.models.moviedetails.MovieDetails
import com.example.domain.models.models.playingNow.MovieDbResultNowPlaying
import com.example.domain.models.models.popular.MovieDbResultPopular
import com.example.domain.models.models.upcoming.MovieDbResultUpcoming
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertMovieDbResultPopular(movie: MovieDbResultPopular)
    suspend fun insertMovieDbResultUpcoming(movie: MovieDbResultUpcoming)
    suspend fun insertMovieDbResultNowPlaying(movie: MovieDbResultNowPlaying)
    suspend fun insertMovieDetails(movie: MovieDetails)

    fun getPopularMovies(page: Int): Flow<MovieDbResultPopular?>
    fun getUpcomingMovies(page: Int): Flow<MovieDbResultUpcoming?>
    fun getNowPlayingMovies(page: Int): Flow<MovieDbResultNowPlaying?>
    fun getMovieDetails(movieId: Int): Flow<MovieDetails?>

}
