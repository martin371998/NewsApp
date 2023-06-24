package com.example.newsapplication.main.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.news.data.model.SearchModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class GlobalViewModel : ViewModel() {
    private val _search = MutableSharedFlow<SearchModel>()
    val search = _search.asSharedFlow()

    fun sendSearchEvent(searchModel: SearchModel) {
        viewModelScope.launch {
            _search.emit(searchModel)
        }
    }
}