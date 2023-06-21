package com.example.newsapplication.news.data.remote

import com.example.newsapplication.core.extentions.dataOrException
import com.example.newsapplication.news.data.model.NewsModel
import com.example.newsapplication.news.data.service.NewsService
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val newsService: NewsService
) : NewsDataSource {
    override suspend fun getNews(search: String): List<NewsModel> = try {
        val response = newsService.getNews(search)
        response.dataOrException("cannot get articles").articles
    } catch (e: Exception) {
        throw e
    }

}