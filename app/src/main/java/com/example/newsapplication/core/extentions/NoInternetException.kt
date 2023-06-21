package com.example.newsapplication.core.extentions

import java.io.IOException


class NoInternetException constructor(msg: String = "") : IOException(msg)