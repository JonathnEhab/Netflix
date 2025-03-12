package com.example.domain.converter

import androidx.room.TypeConverter
import com.example.domain.models.models.following.SpokenLanguage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SpokenLanguageTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromSpokenLanguageList(languages: List<SpokenLanguage>?): String? {
        return gson.toJson(languages)
    }

    @TypeConverter
    fun toSpokenLanguageList(languageString: String?): List<SpokenLanguage>? {
        if (languageString == null) {
            return null
        }
        val type = object : TypeToken<List<SpokenLanguage>>() {}.type
        return gson.fromJson(languageString, type)
    }
}