package com.example.newsapplication.news.data.helper

import androidx.room.TypeConverter
import com.example.newsapplication.news.data.model.SourceModel
import com.google.gson.Gson

class DBConverter {

    private var gson: Gson = Gson()

    @TypeConverter
    fun toSourceModel(sourceModel: String): SourceModel? = try {
        gson.fromJson(sourceModel, SourceModel::class.java)
    } catch (_: Exception) {
        null
    }

    @TypeConverter
    fun fromAddress(sourceModel: SourceModel): String = gson.toJson(sourceModel)
}