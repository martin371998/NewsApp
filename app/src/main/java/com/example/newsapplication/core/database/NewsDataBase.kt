package com.example.newsapplication.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapplication.news.data.helper.DBConverter
import com.example.newsapplication.news.data.helper.DataConsts
import com.example.newsapplication.news.data.helper.NewsDao
import com.example.newsapplication.news.data.model.NewsModel
import com.example.newsapplication.news.data.model.SourceModel

@Database(
    entities = [NewsModel::class, SourceModel::class],
    version = DataConsts.DATABASE_VERSION
)
@TypeConverters(DBConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}