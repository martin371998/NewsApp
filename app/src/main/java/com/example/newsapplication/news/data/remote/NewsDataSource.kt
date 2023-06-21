package com.example.newsapplication.news.data.remote

import com.example.newsapplication.news.data.model.NewsModel


interface NewsDataSource {
    suspend fun getNews(
        search: String
    ): List<NewsModel>
}