package com.example.newsapplication.core.extentions

fun Int?.safe(defaultValue: Int = 0) = this ?: defaultValue