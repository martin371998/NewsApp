package com.example.newsapplication.core.extentions

import com.example.newsapplication.core.exceptions.APIException
import com.example.newsapplication.core.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Response

fun <T> Response<T>.parseErrorResponse(): String? =
    Gson().fromJson(errorBody().toString(), ErrorModel::class.java).message

fun <R> Response<R>.dataOrException(errorMessage: String): R {
    return when (isSuccessful) {
        true -> body() ?: throw Exception(errorMessage)
        else -> throw APIException(parseErrorResponse() ?: errorMessage)
    }
}