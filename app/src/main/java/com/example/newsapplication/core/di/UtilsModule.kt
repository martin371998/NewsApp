package com.example.newsapplication.core.di

import android.content.Context
import com.example.newsapplication.utils.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {
    @Provides
    @Singleton
    fun providesPrefs(@ApplicationContext context: Context): Prefs = Prefs(context)
}