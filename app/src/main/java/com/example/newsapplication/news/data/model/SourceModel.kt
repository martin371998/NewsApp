package com.example.newsapplication.news.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapplication.news.data.helper.DataConsts.TABLE_SOURCE
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity(TABLE_SOURCE)
data class SourceModel(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
) : Parcelable