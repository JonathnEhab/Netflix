package com.example.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.models.models.moviedetails.MovieDetails
import com.example.domain.models.models.playingNow.MovieDbResultNowPlaying
import com.example.domain.models.models.popular.MovieDbResultPopular
import com.example.domain.models.models.upcoming.MovieDbResultUpcoming
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDbResultPopular(movie: MovieDbResultPopular)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDbResultUpcoming(movie: MovieDbResultUpcoming)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDbResultNowPlaying(movie: MovieDbResultNowPlaying)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movie: MovieDetails)

    @Query("SELECT * FROM movie_db_result_popular WHERE page = :page")
    fun getPopularMovies(page: Int): Flow<MovieDbResultPopular?>

    @Query("SELECT * FROM movie_db_result_upcoming WHERE page = :page")
    fun getUpcomingMovies(page: Int): Flow<MovieDbResultUpcoming?>

    @Query("SELECT * FROM movie_db_result_now_playing WHERE page = :page")
    fun getNowPlayingMovies(page: Int): Flow<MovieDbResultNowPlaying?>

    @Query("SELECT * FROM movie_details WHERE id = :movieId")
    fun getMovieDetails(movieId: Int): Flow<MovieDetails?>
}