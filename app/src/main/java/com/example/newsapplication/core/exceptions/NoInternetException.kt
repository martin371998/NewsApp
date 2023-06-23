package com.example.newsapplication.core.exceptions

import java.io.IOException


class NoInternetException constructor(msg: String = "") : IOException(msg)