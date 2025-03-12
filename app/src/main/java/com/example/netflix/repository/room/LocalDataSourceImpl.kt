package com.example.netflix.repository.room

import com.example.domain.models.models.moviedetails.MovieDetails
import com.example.domain.models.models.playingNow.MovieDbResultNowPlaying
import com.example.domain.models.models.popular.MovieDbResultPopular
import com.example.domain.models.models.upcoming.MovieDbResultUpcoming
import com.example.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao)  : LocalDataSource {
    override suspend fun insertMovieDbResultPopular(movie: MovieDbResultPopular) {
        movieDao.insertMovieDbResultPopular(movie)
    }

    override suspend fun insertMovieDbResultUpcoming(movie: MovieDbResultUpcoming) {
        movieDao.insertMovieDbResultUpcoming(movie)
    }

    override suspend fun insertMovieDbResultNowPlaying(movie: MovieDbResultNowPlaying) {
        movieDao.insertMovieDbResultNowPlaying(movie)
    }

    override suspend fun insertMovieDetails(movie: MovieDetails) {
        movieDao.insertMovieDetails(movie)
    }

    override fun getPopularMovies(page: Int): Flow<MovieDbResultPopular?>
        = movieDao.getPopularMovies(page)


    override fun getUpcomingMovies(page: Int): Flow<MovieDbResultUpcoming?>
        = movieDao.getUpcomingMovies(page)


    override fun getNowPlayingMovies(page: Int): Flow<MovieDbResultNowPlaying?>
        = movieDao.getNowPlayingMovies(page)


    override fun getMovieDetails(movieId: Int): Flow<MovieDetails?>
        = movieDao.getMovieDetails(movieId)

}