package com.example.newsapplication.interceptors

import android.content.Context
import com.example.newsapplication.extentions.NoInternetException
import com.example.newsapplication.extentions.isOnline
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (context.isOnline().not()) {
            throw NoInternetException()
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}