package com.example.test_app.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.test_app.R
import com.example.test_app.ui.articles.ArticlesList
import com.example.test_app.ui.navigation.Route
import com.example.test_app.ui.search.SearchBar
import com.example.test_app.ui.search.SearchHistory
import com.example.test_app.ui.view.AppBar
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {
    val state = searchViewModel.searchScreenState
    val filterState = searchViewModel.filterState
    val sortState = searchViewModel.sortState

    Scaffold(
        topBar = {
            Column {
                AppBar(isRounded = true) {
                    SearchBar(
                        filterCount = filterState.count,
                        onFilter = {
                            navController.navigate(Route.Filter.name)
                        },
                        isSorted = sortState != null,
                        onSort = {
                             navController.navigate(Route.SortByBottomSheet.name)
                        },
                        textFieldValue = filterState.queryText,
                        onTextChange = {
                            searchViewModel.setQueryTextFilter(it)
                        },
                        onSearch = {
                            searchViewModel.apply {
                                insertNewQuery(it)
                                getArticles()
                            }
                        }
                    )
                }
            }
        }
    ) {
        val articlesListTitle = state.data?.let {
            stringResource(R.string.articles_list_title_quantity, it.totalArticles)
        } ?: stringResource(R.string.articles_list_title)

        if (state.showSearchResults) {
            ArticlesList(
                title = articlesListTitle,
                isLoading = state.isLoading,
                articles = state.data,
                onArticleClick = { url ->
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigate(route = Route.WebView.getFullRoute(encodedUrl))
                }
            )
        } else if (state.searchHistory?.isNullOrEmpty() == false) {
            SearchHistory(isLoading = state.isLoading, searches = state.searchHistory) {
                searchViewModel.apply {
                    setQueryTextFilter(it)
                    insertNewQuery(it)
                    getArticles()
                }
            }
        }
    }
}
