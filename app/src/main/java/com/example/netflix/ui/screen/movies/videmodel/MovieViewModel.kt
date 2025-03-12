package com.example.netflix.ui.screen.movies.videmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.models.movieresult.MovieResult
import com.example.domain.models.models.playingNow.MovieDbResultNowPlaying
import com.example.domain.models.models.popular.MovieDbResultPopular
import com.example.domain.models.models.upcoming.MovieDbResultUpcoming
import com.example.netflix.repository.remote.RemoteDataSource
import com.example.netflix.repository.room.LocalDataSource
import com.example.netflix.state.ApiState
import com.example.netflix.state.NetworkErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ViewModel() {

    private val _nowPlayingState = MutableStateFlow<ApiState<List<MovieResult>>>(ApiState.Loading)
    val nowPlayingState: StateFlow<ApiState<List<MovieResult>>> = _nowPlayingState

    private val _upcomingState = MutableStateFlow<ApiState<List<MovieResult>>>(ApiState.Loading)
    val upcomingState: StateFlow<ApiState<List<MovieResult>>> = _upcomingState

    private val _popularState = MutableStateFlow<ApiState<List<MovieResult>>>(ApiState.Loading)
    val popularState: StateFlow<ApiState<List<MovieResult>>> = _popularState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        val errorMessage = when (throwable) {
            is java.net.UnknownHostException -> NetworkErrors.NO_INTERNET_CONNECTION
            is java.net.SocketTimeoutException -> NetworkErrors.TIMEOUT_ERROR
            is retrofit2.HttpException -> NetworkErrors.SERVER_ERROR
            else -> NetworkErrors.UNEXPECTED_ERROR
        }
        _nowPlayingState.value = ApiState.Error(errorMessage)
        _upcomingState.value = ApiState.Error(errorMessage)
        _popularState.value = ApiState.Error(errorMessage)
    }

    init {
        fetchNowPlayingMovies(2)
        fetchUpcomingMovies(2)
        fetchPopularMovies(2)
    }

    private fun fetchNowPlayingMovies(page:Int) {
        viewModelScope.launch(exceptionHandler) {
            try {
                val movieDbResultNowPlaying = remoteDataSource.fetchNowPlayingMovies(page = page).first()
                localDataSource.insertMovieDbResultNowPlaying(
                    MovieDbResultNowPlaying(
                        total_pages = movieDbResultNowPlaying.total_pages,
                        total_results = movieDbResultNowPlaying.total_results,
                        results = movieDbResultNowPlaying.results ,
                        page = 2
                    )
                )
                _nowPlayingState.value = ApiState.Success(movieDbResultNowPlaying.results )
            } catch (e: Exception) {

                localDataSource.getNowPlayingMovies(1).collect { cachedMovies ->
                    if (cachedMovies != null && cachedMovies.results.isNotEmpty()) {
                        _nowPlayingState.value = ApiState.Success(cachedMovies.results)
                    } else {
                        _nowPlayingState.value = ApiState.Error(NetworkErrors.NETWORK_ERROR)
                    }
                }
            }
        }
    }



    private fun fetchUpcomingMovies(page:Int) {
        viewModelScope.launch(exceptionHandler) {
            try {
                val movieDbResultUpcoming = remoteDataSource.fetchUpcomingMovies(page = page).first()
                localDataSource.insertMovieDbResultUpcoming(
                    MovieDbResultUpcoming(
                        total_pages = movieDbResultUpcoming.total_pages,
                        total_results = movieDbResultUpcoming.total_results,
                        results = movieDbResultUpcoming.results ,
                        page = 2
                    )
                )
                _upcomingState.value = ApiState.Success(movieDbResultUpcoming.results )
            } catch (e: Exception) {

                localDataSource.getUpcomingMovies(5).collect { cachedMovies ->
                    if (cachedMovies != null && cachedMovies.results.isNotEmpty()) {
                        _upcomingState.value = ApiState.Success(cachedMovies.results)
                    } else {
                        _upcomingState.value = ApiState.Error(NetworkErrors.NETWORK_ERROR)
                    }
                }
            }
        }
    }



    private fun fetchPopularMovies(page:Int) {
        viewModelScope.launch(exceptionHandler) {
            try {
                val movieDbResultPopular = remoteDataSource.fetchPopularMovies(page = page).first()
                localDataSource.insertMovieDbResultPopular(
                    MovieDbResultPopular(
                        total_pages = movieDbResultPopular.total_pages,
                        total_results = movieDbResultPopular.total_results,
                        results = movieDbResultPopular.results ,
                        page = 2
                    )
                )
                _popularState.value = ApiState.Success(movieDbResultPopular.results )
            } catch (e: Exception) {

                localDataSource.getPopularMovies(3).collect { cachedMovies ->
                    if (cachedMovies != null && cachedMovies.results.isNotEmpty()) {
                        _popularState.value = ApiState.Success(cachedMovies.results)
                    } else {
                        _popularState.value = ApiState.Error(NetworkErrors.NETWORK_ERROR)
                    }
                }
            }
        }
    }



}



