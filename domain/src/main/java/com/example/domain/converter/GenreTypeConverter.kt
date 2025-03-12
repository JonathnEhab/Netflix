package com.example.domain.converter

import androidx.room.TypeConverter
import com.example.domain.models.models.following.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromGenreList(genres: List<Genre>?): String? {
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenreList(genreString: String?): List<Genre>? {
        if (genreString == null) {
            return null
        }
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(genreString, type)
    }
}