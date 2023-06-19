package com.example.newsapplication.extentions

import java.io.IOException


class NoInternetException constructor(msg: String = "") : IOException(msg)