package com.example.test_app.feature.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_app.repository.HeadlinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val headlinesRepository: HeadlinesRepository
) : ViewModel() {

    var newsScreenState by mutableStateOf(NewsScreenState())
        private set

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() = viewModelScope.launch {
        newsScreenState = NewsScreenState(isLoading = true)
        headlinesRepository.getTopHeadlines().get(onSuccess = {
            newsScreenState = NewsScreenState(data = it)
        }, onFailure = { _, _ ->
            newsScreenState = NewsScreenState(isError = true)
        })
    }
}
