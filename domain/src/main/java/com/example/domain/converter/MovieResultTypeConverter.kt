package com.example.domain.converter

import androidx.room.TypeConverter
import com.example.domain.models.models.movieresult.MovieResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieResultTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMovieResultList(results: List<MovieResult>?): String? {
        return gson.toJson(results)
    }

    @TypeConverter
    fun toMovieResultList(resultString: String?): List<MovieResult>? {
        if (resultString == null) {
            return null
        }
        val type = object : TypeToken<List<MovieResult>>() {}.type
        return gson.fromJson(resultString, type)
    }
}