package com.example.domain.models.models.popular

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.domain.converter.MovieResultTypeConverter
import com.example.domain.models.models.movieresult.MovieResult

@Entity(tableName = "movie_db_result_popular")
data class MovieDbResultPopular(
    @PrimaryKey
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
    @TypeConverters(MovieResultTypeConverter::class)
    val results: List<MovieResult>
)