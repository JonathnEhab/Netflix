package com.example.remote.apiservice

import com.example.domain.models.models.moviedetails.MovieDetails
import com.example.domain.models.models.playingNow.MovieDbResultNowPlaying
import com.example.domain.models.models.popular.MovieDbResultPopular
import com.example.domain.models.models.upcoming.MovieDbResultUpcoming
import com.example.util.APIKeys
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(APIKeys.GET_UPCOMING_MOVIES_ENDPOINT)
    suspend fun fetchUpcomingMovies(
        @Query(APIKeys.LANGUAGE_PARAM) language: String,
        @Query(APIKeys.PAGE_PARAM) page: Int
    ): MovieDbResultUpcoming

    @GET(APIKeys.GET_POPULAR_MOVIES_ENDPOINT)
    suspend fun fetchPopularMovies(
        @Query(APIKeys.LANGUAGE_PARAM) language: String,
        @Query(APIKeys.PAGE_PARAM) page: Int
    ): MovieDbResultPopular

    @GET(APIKeys.GET_NOW_PLAYING_MOVIES_ENDPOINT)
    suspend fun fetchNowPlayingMovies(
        @Query(APIKeys.LANGUAGE_PARAM) language: String,
        @Query(APIKeys.PAGE_PARAM) page: Int
    ): MovieDbResultNowPlaying

    @GET(APIKeys.GET_MOVIE_DETAILS_ENDPOINT)
    suspend fun fetchMovieDetails(
        @Path(APIKeys.MOVIE_ID_PARAM) movieId: Int,
    ): MovieDetails
}