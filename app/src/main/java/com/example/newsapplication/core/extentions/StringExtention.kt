package com.example.newsapplication.core.extentions

import android.util.Log

fun String?.safe(defaultValue: String = ""): String = this ?: defaultValue