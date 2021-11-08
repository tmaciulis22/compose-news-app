package com.example.test_app.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.test_app.ui.navigation.Route
import com.example.test_app.ui.search.SearchBar
import com.example.test_app.ui.search.SearchHistory
import com.example.test_app.ui.view.AppBar

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {
    val state = searchViewModel.searchScreenState
    val filterState = searchViewModel.filterState

    Scaffold(
        topBar = {
            Column {
                AppBar(isRounded = true) {
                    SearchBar(
                        filterCount = filterState.count,
                        onFilter = {
                            navController.navigate(Route.Filter.name)
                        },
                        onSort = {
                             // TODO
                        },
                        onSearch = {
                            searchViewModel.insertNewQuery(it)
                        }
                    )
                }
            }
        }
    ) {
        if (state.searchHistory?.isNotEmpty() == true)
            SearchHistory(isLoading = state.isLoading, searches = state.searchHistory)
    }
}
