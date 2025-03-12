package com.example.domain.converter

import androidx.room.TypeConverter
import com.example.domain.models.models.following.ProductionCompany
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCompanyTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromProductionCompanyList(companies: List<ProductionCompany>?): String? {
        return gson.toJson(companies)
    }

    @TypeConverter
    fun toProductionCompanyList(companyString: String?): List<ProductionCompany>? {
        if (companyString == null) {
            return null
        }
        val type = object : TypeToken<List<ProductionCompany>>() {}.type
        return gson.fromJson(companyString, type)
    }
}