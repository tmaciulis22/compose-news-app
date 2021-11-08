package com.example.test_app.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_app.api.entity.SearchInAttribute
import com.example.test_app.feature.filter.FilterState
import com.example.test_app.repository.SearchHistoryRepository
import com.example.test_app.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {

    var searchScreenState by mutableStateOf(SearchScreenState())
        private set

    var filterState by mutableStateOf(FilterState())
        private set

    init {
        getSearchHistory()
    }

    fun setFromFilter(from: String) {
        filterState = filterState.copy(from = from)
    }

    fun setToFilter(to: String) {
        filterState = filterState.copy(to = to)
    }

    fun setSearchInFilter(searchInAttribute: SearchInAttribute, isChecked: Boolean) {
        val previousSelections = filterState.searchIn.toMutableList()
        if (isChecked)
            previousSelections.add(searchInAttribute)
        else
            previousSelections.remove(searchInAttribute)
        filterState = filterState.copy(searchIn = previousSelections.toList())
    }

    // Since by default GNews API search in title and description, clearSearchInFilter resets state to these attributes
    fun clearSearchInFilter() {
        filterState = filterState.copy(searchIn = listOf(SearchInAttribute.Title, SearchInAttribute.Description))
    }

    fun clearFilters() {
        filterState = FilterState()
    }

    fun getArticles(
        from: String? = null,
        to: String? = null,
        searchInAttributes: List<SearchInAttribute>? = null
    ) = viewModelScope.launch {
        searchScreenState = SearchScreenState(isLoading = true)
        searchRepository.getArticles(
            from,
            to,
            searchInAttributes
        ).get(onSuccess = {
            searchScreenState = SearchScreenState(data = it)
        }, onFailure = { _, _ ->
            searchScreenState = SearchScreenState(isError = true)
        })
    }

    fun insertNewQuery(queryText: String) = viewModelScope.launch {
        searchScreenState = SearchScreenState(isLoading = true)
        searchHistoryRepository.insertNewQuery(queryText)
        getSearchHistory()
    }

    private fun getSearchHistory() = viewModelScope.launch {
        searchScreenState = SearchScreenState(isLoading = true)
        val searchQueries = searchHistoryRepository.getAllQueries().map { it.queryText }
        searchScreenState = SearchScreenState(searchHistory = searchQueries)
    }
}
