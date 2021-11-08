package com.example.test_app.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_app.api.entity.InAttribute
import com.example.test_app.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    var searchScreenState by mutableStateOf(SearchScreenState())
        private set

    fun getArticles(
        from: String? = null,
        to: String? = null,
        inAttributes: List<InAttribute>? = null
    ) = viewModelScope.launch {
        searchScreenState = SearchScreenState(isLoading = true)
        searchRepository.getArticles(
            from,
            to,
            inAttributes
        ).get(onSuccess = {
            searchScreenState = SearchScreenState(data = it)
        }, onFailure = { _, _ ->
            searchScreenState = SearchScreenState(isError = true)
        })
    }
}
