package com.example.domain.models.models.moviedetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.domain.converter.GenreTypeConverter
import com.example.domain.converter.ProductionCompanyTypeConverter
import com.example.domain.converter.ProductionCountryTypeConverter
import com.example.domain.converter.SpokenLanguageTypeConverter
import com.example.domain.converter.StringListTypeConverter
import com.example.domain.models.models.following.Genre
import com.example.domain.models.models.following.ProductionCompany
import com.example.domain.models.models.following.ProductionCountry
import com.example.domain.models.models.following.SpokenLanguage


@Entity(tableName = "movie_details")
data class MovieDetails(
    @PrimaryKey val id: Int,
    val adult: Boolean,
    val backdrop_path: String?,
    val budget: Int,
    @TypeConverters(GenreTypeConverter::class)
    val genres: List<Genre>,
    val homepage: String?,
    val imdb_id: String?,
    @TypeConverters(StringListTypeConverter::class)
    val origin_country: List<String>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    @TypeConverters(ProductionCompanyTypeConverter::class)
    val production_companies: List<ProductionCompany>,
    @TypeConverters(ProductionCountryTypeConverter::class)
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Long,
    val runtime: Int?,
    @TypeConverters(SpokenLanguageTypeConverter::class)
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
