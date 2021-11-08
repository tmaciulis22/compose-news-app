package com.example.test_app.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R
import com.example.test_app.api.entity.Articles

@Composable
fun SearchHistory(
    isLoading: Boolean = false,
    searches: List<String>? = null,
    onClick: (String) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.grid_unit_9x),),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.grid_unit_8x))
    ) {
        item {
            Text(
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.grid_unit_7x)),
                text = stringResource(R.string.search_history_list_title),
                style = MaterialTheme.typography.h1
            )
        }
        when {
            searches != null -> items(searches) { search ->
                SearchHistoryItem(searchText = search, onClick = onClick)
            }
            isLoading -> items(Articles.MAX_ARTICLES) {
                SearchHistoryItem(isLoading = true)
            }
        }
    }
}

@Preview
@Composable
fun SearchHistoryPreview() {
    SearchHistory(searches = listOf("Quis tristique", "Nibh tempus", "Vulputate tincidunt"))
}
