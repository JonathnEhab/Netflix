package com.example.domain.converter

import androidx.room.TypeConverter
import com.example.domain.models.models.following.ProductionCountry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCountryTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromProductionCountryList(countries: List<ProductionCountry>?): String? {
        return gson.toJson(countries)
    }

    @TypeConverter
    fun toProductionCountryList(countryString: String?): List<ProductionCountry>? {
        if (countryString == null) {
            return null
        }
        val type = object : TypeToken<List<ProductionCountry>>() {}.type
        return gson.fromJson(countryString, type)
    }
}