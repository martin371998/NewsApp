package com.example.newsapplication.news.di

import com.example.newsapplication.news.data.remote.NewsDataSource
import com.example.newsapplication.news.data.remote.NewsDataSourceImpl
import com.example.newsapplication.news.data.repo.NewsRepo
import com.example.newsapplication.news.data.repo.NewsRepoImpl
import com.example.newsapplication.news.data.service.NewsService
import com.example.newsapplication.news.domain.NewsUseCase
import com.example.newsapplication.news.domain.NewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsModule {
    @Singleton
    @Provides
    fun provideArticlesService(
        retrofit: Retrofit
    ): NewsService = retrofit.create(NewsService::class.java)


    @Module
    @InstallIn(SingletonComponent::class)
    abstract class NewsModuleBinder() {
        @Binds
        abstract fun bindNewsDataSource(dataSource: NewsDataSourceImpl): NewsDataSource

        @Binds
        abstract fun bindNewsRepo(repo: NewsRepoImpl): NewsRepo

        @Binds
        abstract fun bindNewsUseCase(useCase: NewsUseCaseImpl): NewsUseCase

    }
}