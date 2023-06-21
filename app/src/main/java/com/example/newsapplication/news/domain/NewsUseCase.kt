package com.example.newsapplication.news.domain

import com.example.newsapplication.core.models.APIResponse
import com.example.newsapplication.news.data.model.NewsModel
import com.example.newsapplication.news.data.model.SearchModel
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {

    suspend fun getNews(
        search: String
    ): Flow<APIResponse<List<NewsModel>>>

    suspend fun getNewsByField(
        searchModel: SearchModel
    ): Flow<APIResponse<List<NewsModel>>>
}