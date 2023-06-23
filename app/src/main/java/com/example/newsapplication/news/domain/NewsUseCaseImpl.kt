package com.example.newsapplication.news.domain

import com.example.newsapplication.core.database.NewsDatabase
import com.example.newsapplication.core.exceptions.NoInternetException
import com.example.newsapplication.core.extentions.safe
import com.example.newsapplication.core.models.APIResponse
import com.example.newsapplication.core.models.Status
import com.example.newsapplication.news.data.model.NewsModel
import com.example.newsapplication.news.data.model.SearchModel
import com.example.newsapplication.news.data.model.SearchType
import com.example.newsapplication.news.data.repo.NewsRepo
import com.example.newsapplication.utils.GlobalFunctions
import com.example.newsapplication.utils.Prefs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class NewsUseCaseImpl @Inject constructor(
    private val newsRepo: NewsRepo, private val newsDatabase: NewsDatabase, private val prefs: Prefs
) : NewsUseCase {
    override suspend fun getNews(
        search: String
    ): Flow<APIResponse<List<NewsModel>>> {
        val result = if (GlobalFunctions.hasOneDayPassed(prefs.lastSync.safe())) {
            prefs.lastSync = LocalDateTime.now().toString()
            newsRepo.getNews(search).map { apiResponse ->
                if (apiResponse.status == Status.ERROR && apiResponse.exception is NoInternetException) {
                    apiResponse.apply { data = newsDatabase.getNewsDao().getAllArticles() }
                }
                apiResponse.data?.let { newsDatabase.getNewsDao().upsertAll(it) }
                apiResponse
            }
        } else {
            flow {
                emit(APIResponse.Loading())
                delay(500)
                emit(APIResponse.Success(newsDatabase.getNewsDao().getAllArticles()))
            }
        }
        return result
    }

    override suspend fun getNewsByField(searchModel: SearchModel): Flow<APIResponse<List<NewsModel>>> =
        flow {
            emit(APIResponse.Loading())
            emit(
                APIResponse.Success(
                    when (searchModel.type) {
                        SearchType.TITLE -> newsDatabase.getNewsDao()
                            .getArticlesByTitle(searchModel.searchQuery)

                        SearchType.DESCRIPTION -> newsDatabase.getNewsDao()
                            .getArticlesByDescription(searchModel.searchQuery)

                        SearchType.AUTHOR -> newsDatabase.getNewsDao()
                            .getArticlesByAuthor(searchModel.searchQuery)

                        SearchType.CONTENT -> newsDatabase.getNewsDao()
                            .getArticlesByContent(searchModel.searchQuery)
                    }
                )
            )
        }

}