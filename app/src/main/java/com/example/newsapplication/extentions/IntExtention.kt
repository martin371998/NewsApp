package com.example.newsapplication.extentions

fun Int?.safe(defaultValue: Int = 0) = this ?: defaultValue