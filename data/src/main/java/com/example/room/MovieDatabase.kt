package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.converter.GenreTypeConverter
import com.example.domain.converter.MovieResultTypeConverter
import com.example.domain.converter.ProductionCompanyTypeConverter
import com.example.domain.converter.ProductionCountryTypeConverter
import com.example.domain.converter.SpokenLanguageTypeConverter
import com.example.domain.converter.StringListTypeConverter
import com.example.domain.models.models.moviedetails.MovieDetails
import com.example.domain.models.models.movieresult.MovieResult
import com.example.domain.models.models.playingNow.MovieDbResultNowPlaying
import com.example.domain.models.models.popular.MovieDbResultPopular
import com.example.domain.models.models.upcoming.MovieDbResultUpcoming

@Database(entities = [
    MovieDbResultPopular::class,
    MovieDbResultUpcoming::class,
    MovieDbResultNowPlaying::class,
    MovieDetails::class], version = 2, exportSchema = false)
@TypeConverters(
    MovieResultTypeConverter::class,
    GenreTypeConverter::class,
    ProductionCompanyTypeConverter::class,
    ProductionCountryTypeConverter::class,
    SpokenLanguageTypeConverter::class,
    StringListTypeConverter::class
)
abstract class MovieDatabase :RoomDatabase() {
  abstract fun getDao():MovieDao
}