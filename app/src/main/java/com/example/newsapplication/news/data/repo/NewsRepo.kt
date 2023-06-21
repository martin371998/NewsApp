package com.example.newsapplication.news.data.repo

import com.example.newsapplication.core.models.APIResponse
import com.example.newsapplication.news.data.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsRepo {
    suspend fun getNews(
        search: String
    ): Flow<APIResponse<List<NewsModel>>>
}