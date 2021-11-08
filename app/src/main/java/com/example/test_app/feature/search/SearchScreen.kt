package com.example.test_app.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.test_app.ui.search.SearchBar
import com.example.test_app.ui.search.SearchHistory
import com.example.test_app.ui.view.AppBar

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val state = searchViewModel.searchScreenState

    Scaffold(
        topBar = {
            Column {
                AppBar(isRounded = true) {
                    SearchBar(onSearch = {
                        searchViewModel.insertNewQuery(it)
                    })
                }
            }
        }
    ) {
        if (state.searchHistory?.isNotEmpty() == true)
            SearchHistory(isLoading = state.isLoading, searches = state.searchHistory)
    }
}
