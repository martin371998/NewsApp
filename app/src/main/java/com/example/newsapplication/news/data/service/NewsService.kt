package com.example.newsapplication.news.data.service

import com.example.newsapplication.core.models.GlobalResponse
import com.example.newsapplication.news.data.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET(GET_EVERYTHING)
    suspend fun getNews(
        @Query("q") search: String = "google"
    ): Response<GlobalResponse<List<NewsModel>>>

    companion object {
        const val GET_EVERYTHING = "everything"
    }
}