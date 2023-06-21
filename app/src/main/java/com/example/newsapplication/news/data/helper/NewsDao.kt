package com.example.newsapplication.news.data.helper

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.newsapplication.news.data.model.NewsModel

@Dao
interface NewsDao {
    @Upsert
    suspend fun upsertAll(articles: List<NewsModel>)

    @Query("SELECT * FROM ${DataConsts.TABLE_ARTICLES}")
    fun getAllArticles(): List<NewsModel>

    @Query("DELETE FROM  ${DataConsts.TABLE_ARTICLES}")
    suspend fun clearAll()

    @Query("SELECT * FROM ${DataConsts.TABLE_ARTICLES} WHERE LOWER(title) LIKE '%' || LOWER(:search) ||'%' ")
    suspend fun getArticlesByTitle(search: String): List<NewsModel>

    @Query("SELECT * FROM ${DataConsts.TABLE_ARTICLES} WHERE LOWER(description) LIKE '%' || LOWER(:search) ||'%' ")
    suspend fun getArticlesByDescription(search: String): List<NewsModel>

    @Query("SELECT * FROM ${DataConsts.TABLE_ARTICLES} WHERE LOWER(author) LIKE '%' || LOWER(:search) ||'%' ")
    suspend fun getArticlesByAuthor(search: String): List<NewsModel>

    @Query("SELECT * FROM ${DataConsts.TABLE_ARTICLES} WHERE LOWER(content) LIKE '%' || LOWER(:search) ||'%' ")
    suspend fun getArticlesByContent(search: String): List<NewsModel>
}