package com.example.test_app.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_app.api.entity.SearchInAttribute
import com.example.test_app.api.entity.SortBy
import com.example.test_app.extension.formatDateToISO
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

    fun setQueryTextFilter(queryText: String) {
        filterState = filterState.copy(queryText = queryText)
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

    fun setSortBy(sortBy: SortBy) {
        filterState = filterState.copy(sortBy = sortBy)
    }

    fun clearSearchInFilter() {
        filterState = filterState.copy(searchIn = listOf())
    }

    fun clearFilters() {
        filterState = filterState.copy(
            from = null,
            to = null,
            searchIn = listOf(),
            sortBy = null,
        )
    }

    fun getArticles(
        queryText: String? = filterState.queryText,
        from: String? = filterState.from.formatDateToISO(),
        to: String? = filterState.to.formatDateToISO(),
        searchInAttributes: List<SearchInAttribute>? = filterState.searchIn,
        sortBy: SortBy? = filterState.sortBy
    ) = viewModelScope.launch {
        searchScreenState = SearchScreenState(isLoading = true, showSearchResults = true)

        if (queryText.isNullOrBlank()) {
            getSearchHistory()
            return@launch
        }

        searchRepository.getArticles(
            queryText,
            from,
            to,
            searchInAttributes,
            sortBy
        ).get(onSuccess = {
            searchScreenState = SearchScreenState(data = it, showSearchResults = true)
        }, onFailure = { _, _ ->
            searchScreenState = SearchScreenState(isError = true)
        })
    }

    fun insertNewQuery(queryText: String) = viewModelScope.launch {
        if (queryText.isBlank()) return@launch

        searchHistoryRepository.insertNewQuery(queryText)
    }

    private fun getSearchHistory() = viewModelScope.launch {
        searchScreenState = SearchScreenState(isLoading = true)
        val searchQueries = searchHistoryRepository.getAllQueries().map { it.queryText }
        searchScreenState = SearchScreenState(searchHistory = searchQueries)
    }
}
