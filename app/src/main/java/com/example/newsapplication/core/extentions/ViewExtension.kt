package com.example.newsapplication.core.extentions

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible

fun View.gone() = apply { this.isGone = true }
fun View.visible() = apply { this.isVisible = true }