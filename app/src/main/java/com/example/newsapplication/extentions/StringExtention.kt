package com.example.newsapplication.extentions

import android.util.Log

fun String?.safe(defaultValue: String = ""): String = this ?: defaultValue

fun String.printLog(log: String = "USERS LIST APP") = apply { Log.e(log, this) }