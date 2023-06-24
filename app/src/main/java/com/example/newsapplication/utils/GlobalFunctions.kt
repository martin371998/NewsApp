package com.example.newsapplication.utils

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object GlobalFunctions {
    fun hasOneDayPassed(oldTime: String): Boolean = try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        val localDateTime = LocalDateTime.parse(oldTime, formatter)
        val result = Duration.between(localDateTime, LocalDateTime.now()).toDays()
        result >= 1
    } catch (_: Exception) {
        true
    }
}