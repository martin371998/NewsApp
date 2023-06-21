package com.example.newsapplication.news.data.repo

import com.example.newsapplication.core.models.APIResponse
import com.example.newsapplication.news.data.model.NewsModel
import com.example.newsapplication.news.data.remote.NewsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val newsDataSource: NewsDataSource
) : NewsRepo {
    override suspend fun getNews(
        search: String
    ): Flow<APIResponse<List<NewsModel>>> = flow {
        emit(APIResponse.Loading())
        try {
            val response = newsDataSource.getNews(search)
            emit(APIResponse.Success(response))
        } catch (e: Exception) {
            emit(APIResponse.Error(e))
        }
    }
}