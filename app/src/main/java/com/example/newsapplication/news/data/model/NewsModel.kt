package com.example.newsapplication.news.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapplication.news.data.helper.DataConsts.TABLE_NEWS
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity(TABLE_NEWS)
data class NewsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("author")
    val author: String?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("source")
    val source: SourceModel?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("urlToImage")
    val urlToImage: String?
) : Parcelable